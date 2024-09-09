package com.softaliance.authentication.exceptions;

import com.softaliance.authentication.responses.AuthenticationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AuthenticationResponse> Exception(Exception ex) {
        AuthenticationResponse apiResponse = new AuthenticationResponse("99",
                "An unexpected error occurred, try again later",
                null);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity<AuthenticationResponse> handleInsufficientAuthenticationException(InsufficientAuthenticationException ex) {
        AuthenticationResponse apiResponse = new AuthenticationResponse("99", "Access Denied", null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);
    }
}
