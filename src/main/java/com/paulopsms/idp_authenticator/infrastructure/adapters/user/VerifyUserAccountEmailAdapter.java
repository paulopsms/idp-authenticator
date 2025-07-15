package com.paulopsms.idp_authenticator.infrastructure.adapters.user;

import com.paulopsms.idp_authenticator.application.gateways.VerifyUserAccountEmailGateway;
import com.paulopsms.idp_authenticator.domain.entities.user.User;
import com.paulopsms.idp_authenticator.domain.exceptions.BusinessException;
import com.paulopsms.idp_authenticator.infrastructure.service.EmailService;

public class VerifyUserAccountEmailAdapter implements VerifyUserAccountEmailGateway {

    private static final String URL = "http://localhost:8091";

    private final EmailService emailService;

    public VerifyUserAccountEmailAdapter(EmailService emailService) {
        this.emailService = emailService;
    }


    @Override
    public void sendEmail(User user) throws BusinessException {
        String subject = "Here is your link to verify your account";
        String body = "Hello " + user.getName() + ",\n\n" +
                "Here is your link to verify your account: " +
                "<h4><a href=\"" + URL + "/users/verify-account?token=" + user.getToken() + "\" " +
                "target=\"_self\">Click here to verify your account</a></h4>" +
                "\n\n" +
                "Best regards,\n" +
                "FinMan support.";

        this.emailService.sendEmail(user.getEmail(), subject, body);
    }
}
