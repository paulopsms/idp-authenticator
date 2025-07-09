package com.paulopsms.idp_authenticator.infrastructure.adapters;

import com.paulopsms.idp_authenticator.application.gateways.RefreshTokenGateway;
import com.paulopsms.idp_authenticator.domain.exceptions.BusinessException;
import com.paulopsms.idp_authenticator.infrastructure.service.JwtService;

public class RefreshTokenAdapter implements RefreshTokenGateway {

    private final JwtService jwtService;

    public RefreshTokenAdapter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public String verifyToken(String token) throws BusinessException {
        return this.jwtService.verifyToken(token);
    }
}
