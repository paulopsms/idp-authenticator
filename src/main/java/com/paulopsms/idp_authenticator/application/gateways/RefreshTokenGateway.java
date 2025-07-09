package com.paulopsms.idp_authenticator.application.gateways;

import com.paulopsms.idp_authenticator.domain.exceptions.BusinessException;

public interface RefreshTokenGateway {

    String verifyToken(String token) throws BusinessException;
}
