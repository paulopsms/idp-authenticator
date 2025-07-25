package com.paulopsms.idp_authenticator.application.gateways;

import com.paulopsms.idp_authenticator.domain.exceptions.BusinessException;

public interface JwtGateway {

    String generateToken(String subject, Integer expirationTime) throws BusinessException;
}
