package com.personal.hotdeal.auth.presentation;

import com.personal.hotdeal.auth.application.AuthService;
import com.personal.hotdeal.auth.presentation.dto.LoginRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpHeaders.SET_COOKIE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody @Validated LoginRequestDto loginRequestDto, HttpServletRequest request){
        ResponseCookie cookie =authService.login(loginRequestDto,request);
        return ResponseEntity.ok().header(SET_COOKIE,cookie.toString()).build();
    }
}
