package com.project.yjshop.web.api;

import com.project.yjshop.domain.user.basket.Basket;
import com.project.yjshop.security.auth.AuthDetails;
import com.project.yjshop.service.user.UserService;
import com.project.yjshop.web.payload.request.user.UserProductRequest;
import com.project.yjshop.web.payload.response.user.UserProductResponse;
import com.project.yjshop.web.payload.response.user.UserPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @GetMapping("/page")
    public UserPageResponse userPage() {
        return null;
    }

    @PostMapping("/purchase")
    public UserProductResponse purchase(@RequestBody @Valid UserProductRequest userProductRequest,
                                        BindingResult bindingResult,
                                        @AuthenticationPrincipal AuthDetails authDetails) {
        return userService.purchase(userProductRequest, bindingResult, authDetails);
    }

    @GetMapping("/basket")
    public List<Basket> myBasket(@AuthenticationPrincipal AuthDetails authDetails) {
        return userService.myBasket(authDetails);
    }

    @PostMapping("/basket")
    public UserProductResponse basket(@RequestBody @Valid UserProductRequest userProductRequest,
                                      BindingResult bindingResult,
                                      @AuthenticationPrincipal AuthDetails authDetails) {
        return userService.basket(userProductRequest, bindingResult, authDetails);
    }

    @DeleteMapping("/basket/{boardId}")
    public UserProductResponse delBasket(@PathVariable Integer boardId,
                                         @AuthenticationPrincipal AuthDetails authDetails) {
        return userService.delBasket(boardId, authDetails);
    }
}
