package com.personal.hotdeal.member.application;

import com.personal.hotdeal.common.exception.CustomException;
import com.personal.hotdeal.common.exception.ErrorCode;
import com.personal.hotdeal.member.application.dto.MemberResponseDto;
import com.personal.hotdeal.member.domain.Member;
import com.personal.hotdeal.member.domain.Role;
import com.personal.hotdeal.member.domain.repository.MemberRepository;
import com.personal.hotdeal.member.presentation.dto.JoinRequestDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public void join(JoinRequestDto joinRequestDto){
        memberRepository.save(createUser(joinRequestDto));
    }
    public void deleteUser(HttpServletRequest request){
        HttpSession session= request.getSession(false);
        Member member=(Member)session.getAttribute(findCookie(request).getValue());
        memberRepository.delete(member);
    }
    public MemberResponseDto getMember(HttpServletRequest request){
        HttpSession session= request.getSession(false);
        Member member=(Member)session.getAttribute(findCookie(request).getValue());
        return MemberResponseDto.builder().email(member.getEmail()).build();
    }
    private Member createUser(JoinRequestDto joinRequestDto){
        duplicateInspectionEmail(joinRequestDto.getEmail());
        return Member.builder()
                .email(joinRequestDto.getEmail())
                .password(joinRequestDto.getPassword())
                .role(Role.USER)
                .build();
    }

    private void duplicateInspectionEmail(String email){
        if(memberRepository.findByEmail(email).isPresent()){
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }
    }

    private Cookie findCookie(HttpServletRequest request){
        Cookie[] cookies =request.getCookies();
        Cookie findCookie=null;
        for (Cookie cookie : cookies) {
            if ("sessionId".equals(cookie.getName())) {
                findCookie=cookie;
            }
        }
        return findCookie;
    }
}
