package com.suffixit.kieb.service.impl;

import com.suffixit.kieb.config.JWTTokenHelper;
import com.suffixit.kieb.dto.UpdatePasswordRequest;
import com.suffixit.kieb.entities.Users;
import com.suffixit.kieb.model.LoginRequest;
import com.suffixit.kieb.model.LoginResponse;
import com.suffixit.kieb.repository.DistrictRepository;
import com.suffixit.kieb.repository.UserRepository;
import com.suffixit.kieb.service.AuthService;
import com.suffixit.kieb.service.DivisionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @author Mahfuz
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final JWTTokenHelper jwtTokenHelper;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;


    @Override
    public LoginResponse signIn(LoginRequest loginRequest) throws InvalidKeySpecException, NoSuchAlgorithmException {
        log.info("Logging in by user: {}", loginRequest.getUserName());

        Authentication authenticate = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken
                        (loginRequest.getUserName(),
                                loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authenticate);
            Users users = (Users) authenticate.getPrincipal();
            String jwt = jwtTokenHelper.generateToken(users);


        return LoginResponse.builder().token(jwt).build();

    }

    @Override
    @Transactional
    public boolean updatePassword(UpdatePasswordRequest updatePasswordRequest) {

        Authentication authentication = authenticateUser(updatePasswordRequest);

        if (authentication.isAuthenticated()) {
            Users user = userRepository.findByUserName(updatePasswordRequest.getUserName())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

            user.setPassword(passwordEncoder.encode(updatePasswordRequest.getNewPassword()));
            userRepository.save(user);
            String email = user.getEmail();
            String updatedPassword = updatePasswordRequest.getNewPassword();

            sendUpdateEmail(email , updatedPassword);

            return true;
        } else {
            return false;
        }
    }

    private Authentication authenticateUser(UpdatePasswordRequest updatePasswordRequest) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                updatePasswordRequest.getUserName(),
                updatePasswordRequest.getOldPassword()
        ));
    }


    private void sendUpdateEmail(String email , String updatedPassword) {
        String body = "Dear User,\n\n"
                + "Your password has been successfully updated.\n\n"
                + "Your updated password is as follows:\n\n"
                + "Updated Password: " + updatedPassword + "\n\n"
                + "Please log in using your updated credentials.\n\n"
                + "Thank you,\nYourCompany Support";
        String subject = "Password Update Successful";

        String toEmail = email;

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("kuet1990@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
    }


}
