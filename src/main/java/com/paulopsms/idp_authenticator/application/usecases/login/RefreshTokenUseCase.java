package com.paulopsms.idp_authenticator.application.usecases.login;

import com.paulopsms.idp_authenticator.application.dto.RefreshTokenRequest;
import com.paulopsms.idp_authenticator.application.dto.TokenResponse;
import com.paulopsms.idp_authenticator.application.gateways.JwtGateway;
import com.paulopsms.idp_authenticator.application.gateways.RefreshTokenGateway;
import com.paulopsms.idp_authenticator.application.gateways.UserRepositoryGateway;
import com.paulopsms.idp_authenticator.domain.entities.user.User;
import com.paulopsms.idp_authenticator.domain.exceptions.BusinessException;

import java.util.UUID;

public class RefreshTokenUseCase {

    private final RefreshTokenGateway refreshTokenGateway;

    private final UserRepositoryGateway userRepositoryGateway;

    private final JwtGateway jwtGateway;

    public RefreshTokenUseCase(RefreshTokenGateway refreshTokenGateway, UserRepositoryGateway userRepositoryGateway, JwtGateway jwtGateway) {
        this.refreshTokenGateway = refreshTokenGateway;
        this.userRepositoryGateway = userRepositoryGateway;
        this.jwtGateway = jwtGateway;
    }

    public TokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest) throws BusinessException {
        String userId = this.refreshTokenGateway.verifyToken(refreshTokenRequest.refreshToken());

        User user = this.userRepositoryGateway.findById(UUID.fromString(userId)).orElseThrow();

        String token = this.jwtGateway.generateToken(user.getEmail(), 15);
        String refreshToken = this.jwtGateway.generateToken(user.getId().toString(), 60);

        return new TokenResponse(token, refreshToken);
    }
}
