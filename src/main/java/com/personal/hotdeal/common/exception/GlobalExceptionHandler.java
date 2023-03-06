package com.personal.hotdeal.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    private ResponseEntity<ErrorResponse> handlerCustomException(CustomException exception){
        return ResponseEntity.status(exception.getErrorCode().getStatus())
                .body(ErrorResponse.builder()
                        .code(exception.getErrorCode().getCode())
                        .message(exception.getMessage()).build());
    }
}
