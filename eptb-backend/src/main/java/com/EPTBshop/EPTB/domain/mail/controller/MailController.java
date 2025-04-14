package com.EPTBshop.EPTB.domain.mail.controller;

import com.EPTBshop.EPTB.domain.mail.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    // 인증번호 전송
    @PostMapping("sendEmail")
    public ResponseEntity<Map<String, Object>> sendEmail(@RequestBody Map<String, Object> emailData) {

        log.info("sendEmail api 진입");

        return mailService.sendEmail(emailData);
    }

    // 인증번호 검증
    @PostMapping("verifyAuthCode")
    public ResponseEntity<Map<String, Object>> verifyAuthCode(@RequestBody Map<String, Object> authData) {
        int inputAuthCode = Integer.parseInt(authData.get("authCode").toString());  // 클라이언트에서 입력한 인증번호

        boolean isValid = mailService.verifyAuthCode(inputAuthCode);

        HashMap<String, Object> responseMap = new HashMap<>();

        if (isValid) {
            responseMap.put("message", "인증번호가 확인되었습니다.");
            return ResponseEntity.ok().body(responseMap);
        } else {
            responseMap.put("message", "인증번호가 틀렸습니다.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);  // 인증번호 틀린 경우
        }
    }
}
