package com.project.yjshop.web.api;

import com.project.yjshop.service.auth.AuthService;
import com.project.yjshop.web.payload.request.auth.JoinRequest;
import com.project.yjshop.web.payload.request.auth.LoginRequest;
import com.project.yjshop.web.payload.response.auth.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {

    private final AuthService authService;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/join")
    public void joinUser(@RequestBody @Valid JoinRequest joinRequest) {
        authService.join(joinRequest);
    }

    @PostMapping("/login")
    public TokenResponse loginUser(@RequestBody @Valid LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/reissue")
    public TokenResponse reissue(@RequestHeader(name = "YJ-refresh") String token) {
        return authService.reissue(token);
    }
}
