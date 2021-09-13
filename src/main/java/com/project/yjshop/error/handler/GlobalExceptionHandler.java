package com.project.yjshop.error.handler;

import com.project.yjshop.error.CMRespDto;
import com.project.yjshop.error.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> customValidationException(CustomException e) {
        return new ResponseEntity<>(new CMRespDto<>(e.getErrorCode().getMessage(), e.getErrorMap()),
                HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }
  }
