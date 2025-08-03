package org.project.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResultDto {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
}
