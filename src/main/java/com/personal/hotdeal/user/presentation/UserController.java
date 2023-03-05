package com.personal.hotdeal.user.presentation;


import com.personal.hotdeal.user.presentation.dto.JoinRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @PostMapping
    public ResponseEntity<Void> join(@RequestBody JoinRequestDto joinRequestDto){
        
        return ResponseEntity.ok().build();
    }
}
