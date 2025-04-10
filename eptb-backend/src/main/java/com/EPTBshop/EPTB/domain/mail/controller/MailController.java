package com.EPTBshop.EPTB.domain.mail.controller;

import com.EPTBshop.EPTB.domain.mail.service.MailService;
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
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("sendEmail")
    public ResponseEntity<Map<String, Object>> sendEmail(@RequestBody Map<String, Object> emailData) {

        log.info("sendEmail api 진입");

        return mailService.sendEmail(emailData);
    }
}
