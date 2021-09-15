package com.project.yjshop.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_TOKEN(401, "잘못된 TOKEN 입니다."),
    INVALID_TOKEN_SIGNATURE(401, "잘못된 JWT 서명입니다."),
    EXPIRATION_TOKEN(401, "만료된 JWT 토큰입니다."),
    NOT_SUPPORTED_TOKEN(401, "지원하지 않는 토큰입니다."),
    NOT_REFRESH(400, "유효한 refresh 토큰이 아닙니다."),


    USER_LOGOUT(401, "로그아웃된 사용자 입니다."),
    INVALID_PASSWORD(401, "잘못된 PASSWORD 입니다."),
    USER_ALREADY_EXISTS(409, "USER 가 이미 존재합니다"),
    USERID_NOT_FOUND(404, "USERID 를 찾지 못했습니다"),
    LOGIN_FAILED(400, "LOGIN 실패"),
    JOIN_FAILED(400, "SIGN 실패");

    private final int status;
    private final String message;
}
