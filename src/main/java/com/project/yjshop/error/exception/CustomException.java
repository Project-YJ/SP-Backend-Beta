package com.project.yjshop.error.exception;

import com.project.yjshop.error.ErrorCode;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Getter
public class CustomException extends RuntimeException{
    @Autowired
    private final ErrorCode errorCode;
    private Map<String, String> errorMap;

    public CustomException(ErrorCode errorCode, Map<String, String> errorMap) {
        this.errorCode = errorCode;
        this.errorMap = errorMap;
    }

    public CustomException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
