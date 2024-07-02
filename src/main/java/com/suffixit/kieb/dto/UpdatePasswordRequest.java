package com.suffixit.kieb.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePasswordRequest {

    @Valid
    @NotEmpty(message = "Username cannot be empty")
    String userName;
    @NotEmpty(message = "Old Password cannot be empty")
    @Size(min = 6, message = "Old Password must be at least 6 characters long")
    String oldPassword;
    @NotEmpty(message = " New Password cannot be empty")
    @Size(min = 6, message = "New Password must be at least 6 characters long")
    String newPassword;

}
