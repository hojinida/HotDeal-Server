package com.personal.hotdeal.user.presentation;


import com.personal.hotdeal.user.application.MemberService;
import com.personal.hotdeal.user.presentation.dto.JoinRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @PostMapping("/join")
    public ResponseEntity<Void> join(@RequestBody @Validated JoinRequestDto joinRequestDto){
        memberService.join(joinRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(){
        return ResponseEntity.ok().build();
    }
}
