package com.project.yjshop.web.payload.request.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String userid;
    private String password;
}
