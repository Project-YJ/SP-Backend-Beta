package com.project.yjshop.service.user;

import com.project.yjshop.domain.board.Board;
import com.project.yjshop.domain.board.BoardRepository;
import com.project.yjshop.domain.user.User;
import com.project.yjshop.domain.user.UserRepository;
import com.project.yjshop.error.ErrorCode;
import com.project.yjshop.error.exception.CustomException;
import com.project.yjshop.security.auth.PrincipalDetails;
import com.project.yjshop.web.payload.request.user.PurchaseRequest;
import com.project.yjshop.web.payload.response.user.PurchaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public PurchaseResponse purchase(PurchaseRequest purchaseRequest, PrincipalDetails principalDetails) {

        Board board = boardRepository.findById(purchaseRequest.getBoardPk()).orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
        User user = userRepository.findById(principalDetails.getUser().getId()).get();

        Long price = board.getPrice() * purchaseRequest.getCount();

        if(principalDetails.getUser().getMoney() < price) {
            throw new CustomException(ErrorCode.LACK_MONEY);
        }

        user.removeMoney(price);
        board.removeCount(purchaseRequest.count);
        board.revenue(price);
        board.getUser().plusMoney(price);

        return PurchaseResponse.builder()
                .message("상품 구매 성공")
                .product(board.getTitle())
                .count(purchaseRequest.getCount())
                .price(price)
                .build();
    }
}
