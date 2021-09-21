package com.project.yjshop.web.payload.response.auth;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
}
