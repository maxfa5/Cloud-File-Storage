package org.project.mapper;

import org.project.DTO.AuthResponse;
import org.project.DTO.AuthenticationResultDto;

public class AuthResponseMapper {
    public static AuthResponse toAuthResponse(AuthenticationResultDto authenticationResponse, String message) {
        return new AuthResponse(message, authenticationResponse.getAccessToken(), authenticationResponse.getRefreshToken(), authenticationResponse.getTokenType());
    }

}
