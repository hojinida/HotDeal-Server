package com.personal.hotdeal.auth.presentation;

import com.personal.hotdeal.auth.application.AuthService;
import com.personal.hotdeal.auth.presentation.dto.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody @Validated LoginRequestDto loginRequestDto){
        return ResponseEntity.ok().build();
    }
}
