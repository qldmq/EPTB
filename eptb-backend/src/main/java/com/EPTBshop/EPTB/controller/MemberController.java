package com.EPTBshop.EPTB.controller;

import com.EPTBshop.EPTB.service.MemberService;
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

    @PostMapping("signup")
    public ResponseEntity<Map<String, Object>> signup(@RequestBody Map<String, Object> memberData) {

        log.info("signup api 진입");
        
        return memberService.signup(memberData);
    }
}
