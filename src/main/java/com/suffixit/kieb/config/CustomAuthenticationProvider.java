package com.suffixit.kieb.config;


import com.suffixit.kieb.entities.Users;
import com.suffixit.kieb.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import static com.suffixit.kieb.values.Messages.INVALID_PASSWORD;
import static com.suffixit.kieb.values.Messages.INVALID_USERNAME;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        if (!new BCryptPasswordEncoder().matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException(INVALID_PASSWORD);
        }

        Users user = (Users) userDetails;
        return new UsernamePasswordAuthenticationToken(user, password, userDetails.getAuthorities());

    }
    @Override
    public boolean supports(Class<?> authenticationType) {
        return authenticationType.equals(UsernamePasswordAuthenticationToken.class);
    }
}
