package com.softaliance.authentication.responses;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String code;
    private String message;
    private String token;
}
