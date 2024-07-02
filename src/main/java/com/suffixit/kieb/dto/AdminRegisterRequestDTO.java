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
public class AdminRegisterRequestDTO {
    @NotNull(message = "Please provide a valid email address")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    private String emailId;

    @NotNull(message = "Please provide a password")
    private String password;
}
