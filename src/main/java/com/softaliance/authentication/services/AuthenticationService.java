package com.softaliance.authentication.services;

import com.softaliance.authentication.dto.Employees;
import com.softaliance.authentication.requests.AuthenticationRequest;
import com.softaliance.authentication.responses.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Employees employeeDetails = userService.fetchUserByEmail(request.getEmail());
        String token = "";
        if (employeeDetails == null) {
            return AuthenticationResponse.builder().code("99")
                    .message("Username or password is incorrect")
                    .build();
        }else {
            boolean isValidPassword = passwordEncoder.matches(request.getPassword(), employeeDetails.getPassword());
            if (!isValidPassword) {
                return AuthenticationResponse.builder().code("99")
                        .message("Username or password is incorrect")
                        .build();
            }else {
                token = jwtService.generateToken(employeeDetails);
            }
        }
        return AuthenticationResponse.builder().code("00")
                .message("success")
                .token(token)
                .build();
    }
}
