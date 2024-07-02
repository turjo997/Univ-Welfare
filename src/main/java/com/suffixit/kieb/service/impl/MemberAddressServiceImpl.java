package com.suffixit.kieb.service.impl;


import com.suffixit.kieb.dto.MemberAddressDTO;
import com.suffixit.kieb.entities.*;
import com.suffixit.kieb.enumerations.AddressType;
import com.suffixit.kieb.repository.*;
import com.suffixit.kieb.service.MemberAddressService;
import com.suffixit.kieb.utils.Utils;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Validated
public class MemberAddressServiceImpl implements MemberAddressService {

    private final MemberRepository memberRepository;
    private final CountryRepository countryRepository;


    private final MemberAddressRepository memberAddressRepository;

    private final AddressBookRepository addressBookRepository;

    public MemberAddressServiceImpl(MemberRepository memberRepository ,
                                    CountryRepository countryRepository ,
                                    MemberAddressRepository memberAddressRepository,
                                    AddressBookRepository addressBookRepository) {
        this.memberRepository = memberRepository;
        this.countryRepository = countryRepository;

        this.memberAddressRepository = memberAddressRepository;
        this.addressBookRepository = addressBookRepository;
    }


    /**
     * Save or update addresses of a member.
     * @param memberAddresses request model
     * @return
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public ResponseEntity<String> addMemberAddresses(List<MemberAddressDTO> memberAddresses) {
        Optional<Member> optionalMember = memberRepository.findById(memberAddresses.get(0).getMemberId());
        if (optionalMember.isEmpty()) {
            throw new BadCredentialsException("Member not found with ID :" + memberAddresses.get(0).getMemberId());
        }

        // expiring previous address info if existed.
        List<MemberAddress> existingMemberAddresses = memberAddressRepository.findByMember(optionalMember.get());
        if (existingMemberAddresses.size() > 0) {
            List<MemberAddress> memberAddressList = existingMemberAddresses.stream()
                    .peek(memberAddress -> memberAddress.setTd(LocalDate.now()))
                    .collect(Collectors.toList());
            memberAddressRepository.saveAll(memberAddressList);
        }


        Map<String, Country> countryMap = fetchCountries(memberAddresses);

        List<AddressBook> addressBooks = new ArrayList<>();
        List<MemberAddress> memberAddressesToSave = new ArrayList<>();

        for (MemberAddressDTO memberAddressDTO : memberAddresses) {


            String countryCode = "1";
            Country country = countryMap.get(countryCode);

            if (country == null) {
                throw new BadCredentialsException("Country with code " + countryCode + " not found.");
            }

            AddressBook addressBook = AddressBook.builder()
                    .address1(memberAddressDTO.getAddress1())
                    .address2(memberAddressDTO.getAddress2())
                    .countryCode(country)
                    .zipcode(memberAddressDTO.getZipcode())
                    .addUser(String.valueOf(memberAddressDTO.getMemberId()))
                    .addDate(LocalDateTime.now())
                    .build();

            addressBooks.add(addressBook);

            AddressType addressType = memberAddressDTO.getAddressType();

            MemberAddress memberAddress = MemberAddress.builder()
                    .address(addressBook)
                    .addressType(addressType)
                    .member(optionalMember.get())
                    .ed(LocalDate.now())
                    .td(Utils.getTillDate())
                    .build();

            memberAddressesToSave.add(memberAddress);
        }

        List<AddressBook> savedAddressBooks = addressBookRepository.saveAll(addressBooks);

        for (int i = 0; i < memberAddressesToSave.size(); i++) {
            memberAddressesToSave.get(i).setAddress(savedAddressBooks.get(i));
        }
        memberAddressRepository.saveAll(memberAddressesToSave);

        return ResponseEntity.ok().body("Member Addresses are added");
    }



    private boolean addressExistsForMember(Member member) {
        return memberAddressRepository.existsByMember(member);
    }

    @Override
    public ResponseEntity<?> getAddressById(Integer id) {
        Optional<Member> optionalMember = memberRepository.findById(id);

        if (optionalMember.isPresent()) {

            List<MemberAddress> optionalMemberAddresses = memberAddressRepository.findByMember(optionalMember.get());

            if (optionalMemberAddresses.size() > 0) {

                List<MemberAddressDTO> memberAddressDTOS = optionalMemberAddresses.stream()
                        .map(memberAddress -> {
                            MemberAddressDTO memberAddressDTO = new MemberAddressDTO();
                           // memberAddressDTO.getAddressId(memberAddress.getAddress().getId());
                            memberAddressDTO.setMemberId(memberAddress.getMember().getId());
                            memberAddressDTO.setAddressType(memberAddress.getAddressType());
                            memberAddressDTO.setEd(memberAddress.getEd());
                            memberAddressDTO.setTd(memberAddress.getTd());
                            memberAddressDTO.setAddress1(memberAddress.getAddress().getAddress1());
                            memberAddressDTO.setAddress2(memberAddress.getAddress().getAddress2());
                            memberAddressDTO.setZipcode(memberAddress.getAddress().getZipcode());
                            memberAddressDTO.setCountryCode(memberAddress.getAddress().getCountryCode().getCountryCode());
                            return memberAddressDTO;
                        })
                        .collect(Collectors.toList());

                return ResponseEntity.ok(memberAddressDTOS);

            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
            }

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No member found with Id:" + id);
        }
    }



    @Override
    public ResponseEntity<String> updateAddress(List<MemberAddressDTO> memberAddresses) {
        Integer memberId = memberAddresses.get(0).getMemberId();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Member not found"));

        List<MemberAddress> existingMemberAddresses = memberAddressRepository.findByMember(member);

        if (existingMemberAddresses.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Address not found. Please add a valid address first.");
        }

        Map<String, Country> countryMap = fetchCountries(memberAddresses);

        for (MemberAddressDTO memberAddressDTO : memberAddresses) {

            String countryCode = "1"; // You can modify this based on your DTO
            Country country = countryMap.get(countryCode);

            if (country == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Country with code " + countryCode + " not found.");
            }

            // Find the existing MemberAddress for the given DTO
            MemberAddress existingMemberAddress = existingMemberAddresses.stream()
                    .filter(addr -> addr.getAddress().getId().equals(memberAddressDTO.getAddressId()))
                    .findFirst()
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Member Address with ID " + memberAddressDTO.getAddressId() + " not found."));

            // Update AddressBook
            AddressBook updatedAddressBook = existingMemberAddress.getAddress();
            updatedAddressBook.setAddress1(memberAddressDTO.getAddress1());
            updatedAddressBook.setAddress2(memberAddressDTO.getAddress2());
            updatedAddressBook.setCountryCode(country);
            updatedAddressBook.setZipcode(memberAddressDTO.getZipcode());
            updatedAddressBook.setAddUser(String.valueOf(memberAddressDTO.getMemberId()));
            updatedAddressBook.setAddDate(LocalDateTime.now());

            // Update MemberAddress
            existingMemberAddress.setAddress(updatedAddressBook);
            existingMemberAddress.setAddressType(memberAddressDTO.getAddressType());
            existingMemberAddress.setEd(LocalDate.now());
            existingMemberAddress.setTd(LocalDate.now());

            // Save the updates
            addressBookRepository.save(updatedAddressBook);
            memberAddressRepository.save(existingMemberAddress);
        }

        return ResponseEntity.ok().body("Member Addresses are updated");
    }
//    public ResponseEntity<?> updateAddress(List<MemberAddressDTO> memberAddresses) {
//
//        Integer memberId = memberAddresses.get(0).getMemberId();
//
//        Member member = memberRepository.findById(memberId)
//                .orElseThrow(() -> new BadCredentialsException("Member not found"));
//
//        List<MemberAddress> existingMemberAddresses = memberAddressRepository.findByMember(member);
//
//
//        if (existingMemberAddresses.isEmpty()) {
//            throw new BadCredentialsException("Member not found with ID: " + memberId);
//        }
//
//        Map<String, Country> countryMap = fetchCountries(memberAddresses);
//
//        for (MemberAddressDTO memberAddressDTO : memberAddresses) {
//            String countryCode = "1";
//            Country country = countryMap.get(countryCode);
//
//            if (country == null) {
//                throw new BadCredentialsException("Country with code " + countryCode + " not found.");
//            }
//
//            // Find the existing MemberAddresses for the given DTO
//            List<MemberAddress> matchingAddresses = existingMemberAddresses.stream()
//                    .filter(addr -> addr.getAddress().getId().equals(memberAddressDTO.getAddressId()))
//                    .toList();
//
//            if (matchingAddresses.isEmpty()) {
//                throw new BadCredentialsException("Member Address with ID " + memberAddressDTO.getAddressId() + " not found.");
//            }
//
//            // Assuming you want to update all matching addresses, you can loop through them
//            for (MemberAddress existingMemberAddress : matchingAddresses) {
//                AddressBook updatedAddressBook = existingMemberAddress.getAddress();
//                updatedAddressBook.setAddress1(memberAddressDTO.getAddress1());
//                updatedAddressBook.setAddress2(memberAddressDTO.getAddress2());
//                updatedAddressBook.setCountryCode(country);
//                updatedAddressBook.setZipcode(memberAddressDTO.getZipcode());
//                updatedAddressBook.setAddUser(String.valueOf(memberAddressDTO.getMemberId()));
//                updatedAddressBook.setAddDate(LocalDateTime.now());
//
//                // Update MemberAddress
//                existingMemberAddress.setAddress(updatedAddressBook);
//                existingMemberAddress.setAddressType(memberAddressDTO.getAddressType());
//                existingMemberAddress.setEd(LocalDate.now());
//                existingMemberAddress.setTd(LocalDate.now());
//
//                // Save the updates
//                addressBookRepository.save(updatedAddressBook);
//                memberAddressRepository.save(existingMemberAddress);
//            }
//        }
//
//        return ResponseEntity.ok().body("Member Addresses are updated");
//    }

    private Map<String, Country> fetchCountries(List<MemberAddressDTO> memberAddresses) {
        Set<String> countryCodes = memberAddresses.stream().map(MemberAddressDTO::getCountryCode).collect(Collectors.toSet());
        List<Country> countries = countryRepository.findAllByCountryCodeIn(countryCodes);
        return countries.stream().collect(Collectors.toMap(Country::getCountryCode, Function.identity()));
    }



//    private Map<String, Country> fetchCountries(MemberAddressDTO memberAddress) {
//        String countryCode = memberAddress.getCountryCode();
//
//        if (countryCode != null && !countryCode.isEmpty()) {
//            Country country = countryRepository.findByCountryCode(countryCode);
//
//            if (country != null) {
//                return Collections.singletonMap(countryCode, country);
//            }
//        }
//
//        return Collections.emptyMap();
//    }

//    private Map<Integer, Thana> fetchThanas(List<MemberAddressDTO> memberAddresses) {
//        Set<Integer> thanaIds = memberAddresses.stream().map(MemberAddressDTO::getThanaId).collect(Collectors.toSet());
//        List<Thana> thanas = thanaRepository.findAllById(thanaIds);
//        return thanas.stream().collect(Collectors.toMap(Thana::getId, Function.identity()));
//    }

}



//    public ResponseEntity<?> getAddressById(Integer id) {
//        Optional<Member> optionalMember = memberRepository.findById(id);
//
//        if (optionalMember.isPresent()) {
//
//            Optional<MemberAddress> optionalMemberAddresses = memberAddressRepository.findAllByMember(optionalMember.get());
//
//            if (optionalMemberAddresses.isPresent()) {
//
//                MemberAddressDTO memberAddressDTO = MemberAddressDTO.builder()
//                        .id(optionalMemberAddresses.get().getId())
//                        .memberId(optionalMemberAddresses.get().getMember().getId())
//                        .address1(optionalMemberAddresses.get().getAddress().getAddress1())
//                        .addressType(optionalMemberAddresses.get().getAddressType())
//                        .td(optionalMemberAddresses.get().getTd())
//                        .ed(optionalMemberAddresses.get().getEd())
//                        .countryCode(optionalMemberAddresses.get().getAddress().getCountryCode().getCountryName())
//                        .address2(optionalMemberAddresses.get().getAddress().getAddress2())
//                        .zipcode(optionalMemberAddresses.get().getAddress().getZipcode())
//                        .build();
//
//                return ResponseEntity.ok(memberAddressDTO);
//
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No relevant member address information found");
//            }
//
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No member found with Id:" + id);
//        }
//    }

