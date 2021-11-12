package com.project.yjshop.service.user;

import com.project.yjshop.domain.user.basket.Basket;
import com.project.yjshop.security.auth.AuthDetails;
import com.project.yjshop.web.payload.request.user.UserProductRequest;
import com.project.yjshop.web.payload.response.user.UserProductResponse;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface UserService {
    UserProductResponse purchase(UserProductRequest userProductRequest, AuthDetails authDetails);
    UserProductResponse basket(UserProductRequest userProductRequest, AuthDetails authDetails);
    UserProductResponse delBasket(Integer boardId, AuthDetails authDetails);
    List<Basket> myBasket(AuthDetails authDetails);

}