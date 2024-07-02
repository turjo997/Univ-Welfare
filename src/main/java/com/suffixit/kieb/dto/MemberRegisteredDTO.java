package com.suffixit.kieb.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberRegisteredDTO {


    @NotNull(message = "Member name should not be null")
    private String memberName;

    @NotNull(message = "Please provide a valid email address")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    private String emailId;

    @NotNull(message = "Phone number one should not be null")
    private String phoneOne;

    private String phoneTwo;

    private String rollNo;

    @NotNull(message = "Gender should not be null")
    private String gender;

    @NotNull(message = "Blood group should not be null")
    private String bloodGroup;

}
