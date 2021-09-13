package com.project.yjshop.error.handler;

import com.project.yjshop.error.CMRespDto;
import com.project.yjshop.error.exception.CustomValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PreProcessingHandler {

    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<?> validationException(CustomValidationException e) {
        return new ResponseEntity<>(new CMRespDto<>(e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
    }
}
