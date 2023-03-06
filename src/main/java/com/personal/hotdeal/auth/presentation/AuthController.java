package com.personal.hotdeal.auth.presentation;

import com.personal.hotdeal.auth.presentation.dto.LoginRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/auth")
public class AuthController {
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody @Validated LoginRequestDto loginRequestDto){
        return ResponseEntity.ok().build();
    }
}
