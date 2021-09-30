package com.project.yjshop.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //JWT
    INVALID_TOKEN(401, "잘못된 TOKEN 입니다."),
    INVALID_TOKEN_SIGNATURE(401, "잘못된 서명입니다."),
    EXPIRATION_TOKEN(401, "만료된 토큰입니다."),
    NOT_SUPPORTED_TOKEN(401, "지원하지 않는 토큰입니다."),
    NOT_REFRESH(400, "유효한 refresh 토큰이 아닙니다."),

    //AUTH
    INVALID_PASSWORD(401, "잘못된 PASSWORD 입니다."),
    USER_ALREADY_EXISTS(409, "USER 가 이미 존재합니다."),
    USERID_NOT_FOUND(404, "USERID 를 찾지 못했습니다."),
    LOGIN_FAILED(400, "LOGIN 실패"),
    JOIN_FAILED(400, "SIGN 실패"),

    //BOARD
    USER_NOT_MATCH(401, "유저가 일치하지 않음"),
    CATEGORY_NOT_FOUND(404, "해당카테고리는 존재하지 않습니다."),
    POSTING_FAILED(400, "상품 등록에 실패 했습니다."),
    COUNT_IS_BIG(403, "상품 수량이 부족합니다."),
    BOARD_NOT_FOUND(404, "해당 상품을 찾지 못했습니다."),
    BASKET_NOT_FOUND(404, "해당 상품을 장바구니에서 찾지 못했습니다."),
    LACK_MONEY(418, "잔액이 부족합니다."),
    PURCHASE_FAILED(400, "상품 거래에 실패했습니다."),

    //BASKET
    INPUT_BASKET_FAILED(400, "장바구니 등록에 실패했습니다."),
    BASKET_ALREADY_EXISTS(409, "이미 장바구니에 등록된 상품입니다.");

    private final int status;
    private final String message;
}
