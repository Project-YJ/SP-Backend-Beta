package com.project.yjshop.service.auth;

import com.project.yjshop.web.payload.request.auth.JoinRequest;
import com.project.yjshop.web.payload.response.auth.JoinResponse;
import org.springframework.validation.BindingResult;

public interface AuthService {
    JoinResponse join(JoinRequest joinRequest, BindingResult binding);
}
