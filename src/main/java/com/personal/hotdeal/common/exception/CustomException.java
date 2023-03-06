package com.personal.hotdeal.common.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private ErrorCode errorCode;
    @Builder
    public CustomException(ErrorCode errorCode){
        super(errorCode.getDescription());
        this.errorCode=errorCode;
    }
}
