package com.suffixit.kieb.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.suffixit.kieb.entities.AddressBook;
import com.suffixit.kieb.entities.Thana;
import com.suffixit.kieb.enumerations.AddressType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberAddressDTOtemp {

    private Integer id;

    private AddressType addressType;

    private LocalDate ed;

    private LocalDate td;

    private String address1;
    private String address2;

    //private String thanaId;

    private AddressBook addressBook;

    private String zipcode;
    //private String countryCode;
}
