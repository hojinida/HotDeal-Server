package com.personal.hotdeal.common.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
public enum ErrorCode {
    DUPLICATE_EMAIL(400,"USER_001","이미 존재하는 이메일 입니다.");
    private final int status;
    private final String code;
    private final String description;

    ErrorCode(int status, String code, String description) {
        this.status = status;
        this.code = code;
        this.description = description;
    }
}
