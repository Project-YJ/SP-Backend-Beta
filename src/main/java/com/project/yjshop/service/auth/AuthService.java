package com.project.yjshop.service.auth;

import com.project.yjshop.web.payload.request.auth.JoinRequest;
import com.project.yjshop.web.payload.request.auth.LoginRequest;
import com.project.yjshop.web.payload.response.auth.CustomResponse;
import com.project.yjshop.web.payload.response.auth.TokenResponse;
import org.springframework.validation.BindingResult;

public interface AuthService {
    void join(JoinRequest joinRequest);
    TokenResponse login(LoginRequest loginRequest);
    TokenResponse reissue(String token);
}
