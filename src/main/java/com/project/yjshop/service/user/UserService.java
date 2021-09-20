package com.project.yjshop.service.user;

import com.project.yjshop.security.auth.PrincipalDetails;
import com.project.yjshop.web.payload.request.user.UserProductRequest;
import com.project.yjshop.web.payload.response.user.UserProductResponse;
import org.springframework.validation.BindingResult;

public interface UserService {
    UserProductResponse purchase(UserProductRequest userProductRequest, BindingResult bindingResult, PrincipalDetails principalDetails);
    UserProductResponse basket(UserProductRequest userProductRequest, PrincipalDetails principalDetails);
}
