package com.project.yjshop.service.user;

import com.project.yjshop.domain.board.Board;
import com.project.yjshop.domain.user.basket.Basket;
import com.project.yjshop.security.auth.PrincipalDetails;
import com.project.yjshop.web.payload.request.user.UserProductRequest;
import com.project.yjshop.web.payload.response.user.UserProductResponse;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface UserService {
    UserProductResponse purchase(UserProductRequest userProductRequest, BindingResult bindingResult, PrincipalDetails principalDetails);
    UserProductResponse basket(UserProductRequest userProductRequest, BindingResult bindingResult, PrincipalDetails principalDetails);
    UserProductResponse delBasket(Long boardId, PrincipalDetails principalDetails);
    List<Basket> myBasket(PrincipalDetails principalDetails);

}