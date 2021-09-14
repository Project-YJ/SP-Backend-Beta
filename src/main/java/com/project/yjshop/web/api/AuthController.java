package com.project.yjshop.web.api;

import com.project.yjshop.service.auth.AuthServiceImpl;
import com.project.yjshop.web.payload.request.auth.JoinRequest;
import com.project.yjshop.web.payload.request.auth.LoginRequest;
import com.project.yjshop.web.payload.response.auth.JoinResponse;
import com.project.yjshop.web.payload.response.auth.TokenDto;
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
    public JoinResponse joinUser(@RequestBody @Valid JoinRequest joinRequest,
                                 BindingResult binding) {
        return authService.join(joinRequest, binding);
    }

    @PostMapping("/login")
    public TokenDto loginUser(@RequestBody @Valid LoginRequest loginRequest,
                              BindingResult binding) {
        return null;
    }
}
