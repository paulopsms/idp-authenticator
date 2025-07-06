package com.paulopsms.idp_authenticator.application.usecases;

import com.paulopsms.idp_authenticator.application.dto.SendTokenRequest;
import com.paulopsms.idp_authenticator.application.gateways.EmailGateway;
import com.paulopsms.idp_authenticator.domain.exceptions.BusinessException;
import com.paulopsms.idp_authenticator.application.gateways.UserRepositoryGateway;
import com.paulopsms.idp_authenticator.domain.entities.user.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class SendTokenUseCase {

    private final UserRepositoryGateway userRepositoryGateway;

    private final EmailGateway emailGateway;

    public SendTokenUseCase(UserRepositoryGateway userRepositoryGateway, EmailGateway emailGateway) {
        this.userRepositoryGateway = userRepositoryGateway;
        this.emailGateway = emailGateway;
    }

    public void sendToken(SendTokenRequest request) throws BusinessException {
        User user = userRepositoryGateway.findByEmailIgnoreCase(request.email())
                .orElseThrow(() -> new BusinessException("User not found."));

        String token = UUID.randomUUID().toString();
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(30);

        user.setToken(token);
        user.setTokenExpiration(expirationTime);

        userRepositoryGateway.save(user);
        this.emailGateway.sendEmail(user);
    }
}
