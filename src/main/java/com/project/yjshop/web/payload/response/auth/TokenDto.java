package com.project.yjshop.web.payload.response.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
public class TokenDto {
    private String accessToken;
    private String refreshToken;
}
