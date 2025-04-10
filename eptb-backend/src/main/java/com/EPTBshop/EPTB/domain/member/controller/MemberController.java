package com.EPTBshop.EPTB.domain.member.controller;

import com.EPTBshop.EPTB.domain.member.dto.MemberRequestDto;
import com.EPTBshop.EPTB.domain.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // 회원가입
    @PostMapping("signup")
    public ResponseEntity<Map<String, Object>> signup(@RequestBody MemberRequestDto memberRequestDto) {

        log.info("signup api 진입");

        return memberService.signup(memberRequestDto);
    }

    // 로그인

    // 로그아웃

    // 내 정보 조회

    // 비밀번호 변경
    
    // 회원 탈퇴
}
