package com.personal.hotdeal.user.presentation.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
public class JoinRequestDto {
    private String email;
    private String password;

    @Builder
    public JoinRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
