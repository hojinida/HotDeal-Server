package com.personal.hotdeal.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER("USER","일반")
    ,ADMIN("ADMIN","관리자");

    private final String key;
    private final String value;
}
