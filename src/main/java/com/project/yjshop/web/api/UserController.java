package com.project.yjshop.web.api;

import com.project.yjshop.security.auth.PrincipalDetails;
import com.project.yjshop.service.user.UserServiceImpl;
import com.project.yjshop.web.payload.request.user.PurchaseRequest;
import com.project.yjshop.web.payload.response.user.PurchaseResponse;
import com.project.yjshop.web.payload.response.user.UserPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("/page")
    public UserPageResponse userPage() {
        return null;
    }

    @PostMapping("/purchase")
    public PurchaseResponse purchase(@RequestBody PurchaseRequest purchaseRequest,
                                     @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return userService.purchase(purchaseRequest, principalDetails);
    }
}
