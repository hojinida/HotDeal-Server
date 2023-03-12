package com.personal.hotdeal.member.presentation;


import com.personal.hotdeal.member.application.MemberService;
import com.personal.hotdeal.member.application.dto.MemberResponseDto;
import com.personal.hotdeal.member.presentation.dto.JoinRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;
    @PostMapping
    public ResponseEntity<Void> join(@RequestBody @Validated JoinRequestDto joinRequestDto){
        memberService.join(joinRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<MemberResponseDto> getMember(HttpServletRequest request){
        return ResponseEntity.ok()
                .body(memberService.getMember(request));
    }
    @DeleteMapping("/me")
    public ResponseEntity<Void> delete(HttpServletRequest request){
        memberService.deleteUser(request);
        return ResponseEntity.ok().build();
    }
}
