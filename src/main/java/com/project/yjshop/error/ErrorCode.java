package com.project.yjshop.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_PASSWORD(401, "Invalid Password"),
    INVALID_TOKEN(401, "Invalid Token"),

    USER_ALREADY_EXISTS(409, "User Already Exists"),
    USER_NOT_FOUND(404, "User Not Found"),
    USERNAME_NOT_FOUND(404, "UserName Not Found"),
    LOGIN_FAILED(400, "Login Failed"),
    JOIN_FAILED(400, "Join Failed");

    private final int status;
    private final String message;
}
