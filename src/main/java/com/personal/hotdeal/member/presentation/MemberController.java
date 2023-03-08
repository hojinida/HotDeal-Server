package com.personal.hotdeal.member.presentation;


import com.personal.hotdeal.member.application.MemberService;
import com.personal.hotdeal.member.presentation.dto.JoinRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;
    @PostMapping()
    public ResponseEntity<Void> join(@RequestBody @Validated JoinRequestDto joinRequestDto){
        memberService.join(joinRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> delete(){
        return ResponseEntity.ok().build();
    }
}
