package com.personal.hotdeal.member.application;

import com.personal.hotdeal.common.exception.CustomException;
import com.personal.hotdeal.common.exception.ErrorCode;
import com.personal.hotdeal.member.domain.Member;
import com.personal.hotdeal.member.domain.Role;
import com.personal.hotdeal.member.domain.repository.MemberRepository;
import com.personal.hotdeal.member.presentation.dto.JoinRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public void join(JoinRequestDto joinRequestDto){
        memberRepository.save(createUser(joinRequestDto));
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
}
