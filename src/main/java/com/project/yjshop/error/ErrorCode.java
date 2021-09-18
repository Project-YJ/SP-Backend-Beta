package com.project.yjshop.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_TOKEN(401, "jwt : 잘못된 TOKEN 입니다."),
    INVALID_TOKEN_SIGNATURE(401, "jwt : 잘못된 서명입니다."),
    EXPIRATION_TOKEN(401, "jwt : 만료된 토큰입니다."),
    NOT_SUPPORTED_TOKEN(401, "jwt : 지원하지 않는 토큰입니다."),
    NOT_REFRESH(400, "jwt : 유효한 refresh 토큰이 아닙니다."),

    INVALID_PASSWORD(401, "auth : 잘못된 PASSWORD 입니다."),
    USER_ALREADY_EXISTS(409, "auth : USER 가 이미 존재합니다."),
    USERID_NOT_FOUND(404, "auth : USERID 를 찾지 못했습니다."),
    LOGIN_FAILED(400, "auth : LOGIN 실패"),
    JOIN_FAILED(400, "auth : SIGN 실패"),

    USER_NOT_MATCH(401, "유저가 일치하지 않음"),

    POSTING_FAILED(400, "상품 등록에 실패 했습니다.");

    private final int status;
    private final String message;
}
