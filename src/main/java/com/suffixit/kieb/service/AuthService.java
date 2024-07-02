package com.suffixit.kieb.service;

import com.suffixit.kieb.dto.UpdatePasswordRequest;
import com.suffixit.kieb.model.LoginRequest;
import com.suffixit.kieb.model.LoginResponse;
import org.springframework.http.ResponseEntity;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @author Mahfuz
 */
public interface AuthService {
    LoginResponse signIn(LoginRequest request)
            throws InvalidKeySpecException, NoSuchAlgorithmException;

    boolean updatePassword(UpdatePasswordRequest updatePasswordRequest);


}
