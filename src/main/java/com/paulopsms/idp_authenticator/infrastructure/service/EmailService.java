package com.paulopsms.idp_authenticator.infrastructure.service;

import com.paulopsms.idp_authenticator.domain.exceptions.BusinessException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.UnsupportedEncodingException;

public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    private static final String from = "fm.finman@gmail.com";
    private static final String URL = "http://localhost:8091";
    private static final String title = "FinMan support";

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    //TODO Will be migrated to EmailService microservice

    public void sendEmail(String email, String subject, String body) throws BusinessException {
        MimeMessage message = this.javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        logger.info("sending email... "+ email + " " + subject + " " + body);
        try {
            helper.setFrom(from, title);
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(body, true);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new BusinessException("Error sending e-mail");
        }

        this.javaMailSender.send(message);
    }
}
