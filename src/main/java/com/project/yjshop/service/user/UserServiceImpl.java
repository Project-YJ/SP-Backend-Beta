package com.project.yjshop.service.user;

import com.project.yjshop.domain.board.Board;
import com.project.yjshop.domain.board.BoardRepository;
import com.project.yjshop.domain.user.User;
import com.project.yjshop.domain.user.UserRepository;
import com.project.yjshop.domain.user.basket.Basket;
import com.project.yjshop.domain.user.basket.BasketRepository;
import com.project.yjshop.error.ErrorCode;
import com.project.yjshop.error.exception.CustomException;
import com.project.yjshop.security.auth.PrincipalDetails;
import com.project.yjshop.web.payload.request.user.UserProductRequest;
import com.project.yjshop.web.payload.response.user.UserProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final BasketRepository basketRepository;

    @Override
    @Transactional
    public UserProductResponse purchase(UserProductRequest userProductRequest, BindingResult bindingResult, PrincipalDetails principalDetails) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomException(ErrorCode.PURCHASE_FAILED, errorMap);
        } else {

            Board board = boardRepository.findById(userProductRequest.getBoardPk()).orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
            User user = userRepository.findById(principalDetails.getUser().getId()).get();

            Long price = board.getPrice() * userProductRequest.getCount();

            if (principalDetails.getUser().getMoney() < price) {
                throw new CustomException(ErrorCode.LACK_MONEY);
            }

            user.removeMoney(price);
            board.removeCount(userProductRequest.count);
            board.revenue(price);
            board.getUser().plusMoney(price);

            return UserProductResponse.builder()
                    .message("상품 구매 성공")
                    .product(board.getTitle())
                    .count(userProductRequest.getCount())
                    .price(board.getPrice())
                    .build();
        }
    }

    @Override
    public UserProductResponse basket(UserProductRequest userProductRequest, PrincipalDetails principalDetails) {
        Board board = boardRepository.findById(userProductRequest.getBoardPk()).orElseThrow( ()-> new CustomException(ErrorCode.BOARD_NOT_FOUND));
        basketRepository.save(Basket.builder()
                .count(userProductRequest.getCount())
                .product(board)
                .user(userRepository.findById(principalDetails.getUser().getId()).get())
                .build());
        return UserProductResponse.builder()
                .message("장바구니에 등록되었습니다.")
                .product(board.getTitle())
                .count(board.getCount())
                .price(board.getPrice())
                .build();
    }
}
