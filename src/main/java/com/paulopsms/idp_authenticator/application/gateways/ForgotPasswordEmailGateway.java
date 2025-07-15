package com.paulopsms.idp_authenticator.application.gateways;

import com.paulopsms.idp_authenticator.domain.entities.user.User;
import com.paulopsms.idp_authenticator.domain.exceptions.BusinessException;

public interface ForgotPasswordEmailGateway {

    void sendEmail(User user) throws BusinessException;
}
