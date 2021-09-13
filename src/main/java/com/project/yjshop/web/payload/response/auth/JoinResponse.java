package com.project.yjshop.web.payload.response.auth;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JoinResponse {
    private Long pk;
    private String message;
}
