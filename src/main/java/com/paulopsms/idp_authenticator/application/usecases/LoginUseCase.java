package com.paulopsms.idp_authenticator.application.usecases;

import com.paulopsms.idp_authenticator.application.dto.TokenResponse;
import com.paulopsms.idp_authenticator.application.dto.login.LoginRequest;
import com.paulopsms.idp_authenticator.application.gateways.AuthenticationGateway;
import com.paulopsms.idp_authenticator.application.gateways.JwtGateway;
import com.paulopsms.idp_authenticator.domain.entities.user.User;
import com.paulopsms.idp_authenticator.domain.exceptions.BusinessException;

public class LoginUseCase {

    private final AuthenticationGateway authenticationGateway;

    private final JwtGateway jwtGateway;

    public LoginUseCase(AuthenticationGateway authenticationGateway, JwtGateway jwtGateway) {
        this.authenticationGateway = authenticationGateway;
        this.jwtGateway = jwtGateway;
    }

    public TokenResponse login(LoginRequest loginRequest) throws BusinessException {
        User user = this.authenticationGateway.authenticate(loginRequest.username(), loginRequest.password());

        String token = this.jwtGateway.generateToken(user.getEmail(), 15);
        String refreshToken = this.jwtGateway.generateToken(user.getId().toString(), 60);

        return new TokenResponse(token, refreshToken);
    }
}
