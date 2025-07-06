package com.paulopsms.idp_authenticator.infrastructure.adapters;

import com.paulopsms.idp_authenticator.application.gateways.EmailGateway;
import com.paulopsms.idp_authenticator.domain.entities.user.User;
import com.paulopsms.idp_authenticator.domain.exceptions.BusinessException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.UnsupportedEncodingException;

public class EmailAdapter implements EmailGateway {

    private static final String from = "fm.finman@gmail.com";
    private static final String URL = "http://localhost:8091";
    private static final String title = "FinMan support";

    private final JavaMailSender javaMailSender;

    public EmailAdapter(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    //TODO Will be migrated to EmailService microservice

    @Override
    public void sendEmail(String email, String subject, String body) throws BusinessException {
        MimeMessage message = this.javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        System.out.println("sending email... "+ email + " " + subject + " " + body);
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

    @Override
    public void sendEmail(User user) throws BusinessException {
        String subject = "Here is your link to change your password";
        String body = "Hello " + user.getName() + ",\n\n" +
                "Here is your link to change your password: " +
                "<h4><a href=\"" + URL + "/users/forgot-password?token=" + user.getToken() + "\" " +
                "target=\"_self\">Click here to change your password</a></h4>" +
                "\n\n" +
                "Best regards,\n" +
                "FinMan support";

        this.sendEmail(user.getEmail(), subject, body);
    }
}
