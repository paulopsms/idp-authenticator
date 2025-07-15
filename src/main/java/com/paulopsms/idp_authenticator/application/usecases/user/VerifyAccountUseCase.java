package com.paulopsms.idp_authenticator.application.usecases.user;

import com.paulopsms.idp_authenticator.application.gateways.UserRepositoryGateway;
import com.paulopsms.idp_authenticator.domain.entities.user.User;
import com.paulopsms.idp_authenticator.domain.exceptions.BusinessException;

import java.time.LocalDateTime;

public class VerifyAccountUseCase {

    private final UserRepositoryGateway userRepositoryGateway;

    public VerifyAccountUseCase(UserRepositoryGateway userRepositoryGateway) {
        this.userRepositoryGateway = userRepositoryGateway;
    }

    public void verifyAccount(String token) throws BusinessException {
        User user = userRepositoryGateway.findByToken(token)
                .orElseThrow(() -> new BusinessException("User not found."));

        if (user.getTokenExpiration().isBefore(LocalDateTime.now())) {
            throw new BusinessException("Token has expired.");
        }

        user.setToken(null);
        user.setTokenExpiration(null);
        user.setVerified(true);

        userRepositoryGateway.save(user);
    }
}
