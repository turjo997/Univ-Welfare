package com.suffixit.kieb.dto;

import com.suffixit.kieb.enumerations.AddressType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberAddressDTO {

    private Integer addressId;

    private Integer memberId;

    private AddressType addressType;

    private LocalDate ed;

    private LocalDate td;

    @Size(max = 500, message = "Address1 should not exceed 500 characters")
    private String address1;


    @Size(max = 500, message = "Address2 Name should not exceed 100 characters")
    private String address2;

    private String zipcode;
    private String countryCode;


}
