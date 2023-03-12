package com.personal.hotdeal.auth.application;

import com.personal.hotdeal.auth.presentation.dto.LoginRequestDto;
import com.personal.hotdeal.common.exception.CustomException;
import com.personal.hotdeal.common.exception.ErrorCode;
import com.personal.hotdeal.member.domain.Member;
import com.personal.hotdeal.member.domain.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;

    public ResponseCookie login(LoginRequestDto loginRequestDto, HttpServletRequest request){
        Optional<Member> findMember= memberRepository.findByEmail(loginRequestDto.getEmail());
        if(findMember.isEmpty() ||
                !findMember.get().getPassword().equals(loginRequestDto.getPassword())){
            throw new CustomException(ErrorCode.NOT_MATCH_MEMBER_INFORMATION);
        }
        String sessionId = UUID.randomUUID().toString();
        HttpSession session= request.getSession();
        session.setAttribute(sessionId,findMember.get());
        return ResponseCookie.fromClientResponse("sessionId",sessionId)
                .path("/")
                .build();
    }
}
