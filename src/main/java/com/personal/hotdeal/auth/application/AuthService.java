package com.personal.hotdeal.auth.application;

import com.personal.hotdeal.auth.presentation.dto.LoginRequestDto;
import com.personal.hotdeal.common.exception.CustomException;
import com.personal.hotdeal.common.exception.ErrorCode;
import com.personal.hotdeal.member.domain.Member;
import com.personal.hotdeal.member.domain.repository.MemberRepository;
import jakarta.websocket.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;

    public void login(LoginRequestDto loginRequestDto){
        Optional<Member> findMember= memberRepository.findByEmail(loginRequestDto.getEmail());
        if(findMember.isEmpty() ||
                findMember.get().getPassword().equals(loginRequestDto.getPassword())){
            throw new CustomException(ErrorCode.NOT_MATCH_MEMBER_INFORMATION);
        }
        String sessionId = UUID.randomUUID().toString();

    }
}
