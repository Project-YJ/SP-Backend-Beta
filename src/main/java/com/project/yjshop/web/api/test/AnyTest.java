package com.project.yjshop.web.api.test;

import com.project.yjshop.domain.board.category.Category;
import com.project.yjshop.domain.board.category.CategoryRepository;
import com.project.yjshop.domain.token.RefreshToken;
import com.project.yjshop.domain.token.RefreshTokenRepository;
import com.project.yjshop.domain.user.UserRepository;
import com.project.yjshop.security.auth.AuthDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AnyTest {

    private final RefreshTokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

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
        userRepository.findById(1).get().plusMoney(100000);
        return null;
    }

    @GetMapping("role")
    public String user(@AuthenticationPrincipal AuthDetails authDetails) {
        return authDetails.getUser().getRole().getKey();
    }

    @PostMapping("category")
    public void cate(@RequestBody String name) {
        categoryRepository.save(Category.builder()
                .name(name)
                .build());
    }
}
