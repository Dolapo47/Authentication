package com.softaliance.authentication.controller;

import com.softaliance.authentication.requests.AuthenticationRequest;
import com.softaliance.authentication.responses.AuthenticationResponse;
import com.softaliance.authentication.services.AuthenticationService;
import com.softaliance.authentication.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = authenticationService.authenticate(request);
        if(response.getCode().equals("00")){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(401).body(response);
    }
}
