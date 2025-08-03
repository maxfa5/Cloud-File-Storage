package org.project.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String message;
    private String token;
    private String refreshToken;
    private String tokenType;
    public AuthResponse(String message) {
    this.message = message;
    }

}
