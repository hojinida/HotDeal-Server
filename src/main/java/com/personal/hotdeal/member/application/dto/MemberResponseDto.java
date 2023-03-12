package com.personal.hotdeal.member.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponseDto {
    private String email;
    @Builder
    public MemberResponseDto(String email) {
        this.email = email;
    }
}
