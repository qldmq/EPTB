package com.EPTBshop.EPTB.service;

import com.EPTBshop.EPTB.dto.MemberDto;
import com.EPTBshop.EPTB.entity.Member;
import com.EPTBshop.EPTB.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<Map<String, Object>> signup(Map<String, Object> memberData) {

        HashMap<String, Object> responseMap = new HashMap<>();

        try {
            if (memberRepository.existsByMemberId(memberData.get("memberId").toString())) {
                responseMap.put("message", "이미 존재하는 아이디입니다.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);
            }
            if (memberRepository.existsByNickname(memberData.get("nickname").toString())) {
                responseMap.put("message", "이미 존재하는 닉네임입니다.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);
            }

            MemberDto memberDto = MemberDto.builder()
                    .loginType(0)
                    .memberId(memberData.get("memberId").toString())
                    .password(passwordEncoder.encode(memberData.get("password").toString()))
                    .email(memberData.get("email").toString())
                    .nickname(memberData.get("nickname").toString())
                    .build();

            Member member = Member.toEntity(memberDto);
            memberRepository.save(member);
            responseMap.put("message", "회원가입 성공");
            return ResponseEntity.status(HttpStatus.OK).body(responseMap);
        } catch (Exception e) {
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);
        }


    }
}