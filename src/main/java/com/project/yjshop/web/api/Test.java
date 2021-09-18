package com.project.yjshop.web.api;

import com.project.yjshop.domain.token.RefreshToken;
import com.project.yjshop.domain.token.RefreshTokenRepository;
import com.project.yjshop.domain.user.User;
import com.project.yjshop.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class Test {

    private final RefreshTokenRepository tokenRepository;
    private final UserRepository userRepository;

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

    @GetMapping("money")
    @Transactional
    public String money() {
        userRepository.findById(1L).get().plusMoney(100000L);
        return null;
    }
}
