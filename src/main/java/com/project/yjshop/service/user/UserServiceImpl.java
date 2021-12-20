package com.project.yjshop.service.user;

import com.project.yjshop.domain.board.Board;
import com.project.yjshop.domain.board.BoardRepository;
import com.project.yjshop.domain.user.User;
import com.project.yjshop.domain.user.UserRepository;
import com.project.yjshop.domain.user.basket.Basket;
import com.project.yjshop.domain.user.basket.BasketRepository;
import com.project.yjshop.error.ErrorCode;
import com.project.yjshop.error.exception.CustomException;
import com.project.yjshop.facade.UserFacade;
import com.project.yjshop.security.auth.AuthDetails;
import com.project.yjshop.web.payload.request.user.UserProductRequest;
import com.project.yjshop.web.payload.response.user.UserPageResponse;
import com.project.yjshop.web.payload.response.user.UserProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final BasketRepository basketRepository;
    private final UserFacade userFacade;

    @Override
    @Transactional
    public UserProductResponse purchase(UserProductRequest userProductRequest, AuthDetails authDetails) {

            Board board = boardRepository.findById(userProductRequest.getBoardPk()).orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
            User user = userRepository.findById(authDetails.getUser().getId()).get();

            Integer price = board.getPrice() * userProductRequest.getCount();

            if (authDetails.getUser().getMoney() < price) {
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

    @Override
    @Transactional
    public UserProductResponse basket(UserProductRequest userProductRequest, AuthDetails authDetails) {

            Board board = boardRepository.findById(userProductRequest.getBoardPk())
                    .orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
            try {
                basketRepository.save(Basket.builder()
                        .count(userProductRequest.getCount())
                        .product(board)
                        .user(userRepository.findById(authDetails.getUser().getId()).get())
                        .build());
            } catch (Exception e) {
                throw new CustomException(ErrorCode.BASKET_ALREADY_EXISTS);
            }

            return UserProductResponse.builder()
                    .message("장바구니에 등록되었습니다.")
                    .product(board.getTitle())
                    .count(board.getCount())
                    .price(board.getPrice())
                    .build();
    }

    @Override
    @Transactional
    public UserProductResponse delBasket(Integer boardId, AuthDetails authDetails) {
        Basket del = basketRepository.mBasket(boardId, authDetails.getUser().getId())
                .orElseThrow(()->new CustomException(ErrorCode.BASKET_NOT_FOUND));
        basketRepository.delete(del);
        return UserProductResponse.builder()
                .message("장바구니 삭제 성공")
                .product(del.getProduct().getTitle())
                .count(del.getCount())
                .price(del.getProduct().getPrice())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Basket> myBasket(AuthDetails authDetails) {
        return basketRepository.findAllByUser(authDetails.getUser());
    }

    @Override
    public UserPageResponse getUserPage() {
        return new UserPageResponse(userFacade.getCurrentTeacher().getNickname());
    }
}
