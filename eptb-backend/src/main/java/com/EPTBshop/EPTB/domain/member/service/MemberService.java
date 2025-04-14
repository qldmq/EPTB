package com.EPTBshop.EPTB.domain.member.service;

import com.EPTBshop.EPTB.domain.member.domain.Member;
import com.EPTBshop.EPTB.domain.member.dto.LoginRequestDto;
import com.EPTBshop.EPTB.domain.member.dto.MemberRequestDto;
import com.EPTBshop.EPTB.domain.member.repository.MemberRepository;
import com.EPTBshop.EPTB.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    // 회원가입
    public ResponseEntity<Map<String, Object>> signup(MemberRequestDto memberRequestDto) {

        HashMap<String, Object> responseMap = new HashMap<>();

        try {
            if (memberRepository.existsByMemberId(memberRequestDto.getMemberId())) {
                responseMap.put("message", "이미 존재하는 아이디입니다.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);
            }
            if (memberRepository.existsByNickname(memberRequestDto.getNickname())) {
                responseMap.put("message", "이미 존재하는 닉네임입니다.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);
            }

            Member member = Member.builder()
                    .loginType(0)
                    .memberId(memberRequestDto.getMemberId())
                    .password(passwordEncoder.encode(memberRequestDto.getPassword()))
                    .email(memberRequestDto.getEmail())
                    .nickname(memberRequestDto.getNickname())
                    .role("ROLE_USER")
                    .build();

            memberRepository.save(member);
            responseMap.put("message", "회원가입 성공");

            return ResponseEntity.status(HttpStatus.OK).body(responseMap);
        } catch (Exception e) {
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);
        }
    }

    // 로그인
    public ResponseEntity<Map<String, Object>> login(LoginRequestDto loginRequestDto) {

        HashMap<String, Object> responseMap = new HashMap<>();
        Optional<Member> memberOptional = memberRepository.findByMemberId(loginRequestDto.getMemberId());

        if (memberOptional.isEmpty()) {
            responseMap.put("message", "아이디가 존재하지 않습니다.");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);
        }

        Member member = memberOptional.get();

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), member.getPassword())) {
            responseMap.put("message", "비밀번호가 올바르지 않습니다.");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);
        }

        String accessToken = jwtTokenProvider.createAccessToken(member.getMemberId());

        responseMap.put("message", "로그인 성공");
        responseMap.put("accessToken", accessToken);
        responseMap.put("nickname", member.getNickname());

        return ResponseEntity.status(HttpStatus.OK).body(responseMap);
    }

    //
}