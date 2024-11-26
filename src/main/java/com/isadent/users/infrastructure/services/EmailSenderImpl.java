package com.isadent.users.infrastructure.services;

import com.isadent.users.domain.model.UserCredentials;
import com.isadent.users.domain.services.EmailSender;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EmailSenderImpl implements EmailSender {
    private final JavaMailSender javaMailSender;
    private final LoadTemplate loadTemplate;
    private final JwtUtil jwtUtil;

    public EmailSenderImpl(JavaMailSender javaMailSender, LoadTemplate loadTemplate, JwtUtil jwtUtil) {
        this.javaMailSender = javaMailSender;
        this.loadTemplate = loadTemplate;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void verificationEmail(UserCredentials userCredentials) {

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(userCredentials.getEmail());
            helper.setSubject("CTO Email Verification");
            String token = jwtUtil.generateToken(userCredentials);
            String content = loadTemplate.loadStringHtml(token);
            helper.setText(content, true);
            javaMailSender.send(message);
        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
