package com.project.yjshop.web.api;

import com.project.yjshop.service.auth.AuthServiceImpl;
import com.project.yjshop.web.payload.request.auth.JoinRequest;
import com.project.yjshop.web.payload.request.auth.LoginRequest;
import com.project.yjshop.web.payload.response.auth.CustomResponse;
import com.project.yjshop.web.payload.response.auth.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class AuthController {

    private final AuthServiceImpl authService;

    @PostMapping("/join")
    public CustomResponse<?> joinUser(@RequestBody @Valid JoinRequest joinRequest,
                                   BindingResult binding) {
        return authService.join(joinRequest, binding);
    }

    @PostMapping("/login")
    public TokenResponse loginUser(@RequestBody @Valid LoginRequest loginRequest,
                                   BindingResult binding) {
        return authService.login(loginRequest, binding);
    }

    @PostMapping("/reissue")
    public TokenResponse reissue(@RequestHeader(name = "YJ-refresh") String token) {
        return authService.reissue(token);
    }
}
