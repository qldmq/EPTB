package com.EPTBshop.EPTB.domain.mail.service;

import com.EPTBshop.EPTB.domain.member.repository.MemberRepository;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    private final MemberRepository memberRepository;
    private final JavaMailSender mailSender;

    // 이메일 인증번호 전송
    public ResponseEntity<Map<String, Object>> sendEmail(Map<String, Object> emailData) {

        HashMap<String, Object> responseMap = new HashMap<>();

        String email = emailData.get("email").toString();

        if (memberRepository.existByEmail(email)) {
            responseMap.put("message", "이미 존재하는 이메일입니다.");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);
        }

        SecureRandom secureRandom = new SecureRandom();
        int checkNum = secureRandom.nextInt(900000) + 100000;

        String subject = "EPTB: 이메일 인증번호";
        String text = "<html><body>" +
                "<div class='container' style='max-width: 600px; margin: 0 auto; padding: 20px; background-color: #fff; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);'>" +
                "<div class='header' style='text-align: center; color: #1a73e8; font-size: 24px; font-weight: bold; margin-bottom: 20px;'>EPTB 이메일 인증</div>" +
                "<div class='content' style='font-size: 16px; color: #555; margin-bottom: 20px;'>" +
                "<p>안녕하세요! 아래 인증번호를 입력하여 이메일 인증을 완료해 주세요.</p>" +
                "<p><strong>인증번호: " + checkNum + "</strong></p>" +
                "<p>감사합니다!<br> - EPTB 팀</p>" +
                "</div>" +
                "<div class='footer' style='text-align: center; font-size: 14px; color: #888;'>이 이메일은 자동으로 발송되었습니다. <br>문의사항이 있으시면 support@EPTB.com 으로 연락주세요.</div>" +
                "</div>" +
                "</body></html>";

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

            messageHelper.setTo(email);
            messageHelper.setSubject(subject);
            messageHelper.setText(text, true);

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        responseMap.put("message", checkNum);

        return ResponseEntity.status(HttpStatus.OK).body(responseMap);
    }





}