package com.project.yjshop.web.api;

import com.project.yjshop.domain.token.RefreshToken;
import com.project.yjshop.domain.token.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class Test {

    private final RefreshTokenRepository tokenRepository;

    @GetMapping("abc")
    public String abc() {
        return "abc";
    }

    @GetMapping("test/123")
    public String test() { return "123"; }

    @PostMapping("abc")
    public String redis() {
        tokenRepository.save(RefreshToken.builder()
                .token("abc")
                .build());
        return "success";
    }
}
