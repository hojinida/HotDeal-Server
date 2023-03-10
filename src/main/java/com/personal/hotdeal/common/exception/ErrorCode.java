package com.personal.hotdeal.common.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
public enum ErrorCode {
    DUPLICATE_EMAIL(400,"USER_001","이미 존재하는 이메일 입니다."),
    NOT_MATCH_MEMBER_INFORMATION(400,"USER_002","회원정보가 일치하지 않습니다."),
    USER_UNAUTORIZED(400,"AUTH_001","인증되지 않은 사용자입니다.");
    private final int status;
    private final String code;
    private final String description;

    ErrorCode(int status, String code, String description) {
        this.status = status;
        this.code = code;
        this.description = description;
    }
}
