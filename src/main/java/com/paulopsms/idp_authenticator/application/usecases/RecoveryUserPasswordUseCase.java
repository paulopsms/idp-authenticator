package com.paulopsms.idp_authenticator.application.usecases;

import com.paulopsms.idp_authenticator.application.dto.PasswordRecoveryRequest;
import com.paulopsms.idp_authenticator.application.dto.SendTokenRequest;
import com.paulopsms.idp_authenticator.application.gateways.EmailGateway;
import com.paulopsms.idp_authenticator.domain.exceptions.BusinessException;
import com.paulopsms.idp_authenticator.application.gateways.UserRepositoryGateway;
import com.paulopsms.idp_authenticator.domain.entities.user.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.UUID;

public class RecoveryUserPasswordUseCase {

    private final UserRepositoryGateway userRepositoryGateway;

    private final EmailGateway emailGateway;

    private final PasswordEncoder passwordEncoder;

    public RecoveryUserPasswordUseCase(UserRepositoryGateway userRepositoryGateway, EmailGateway emailGateway, PasswordEncoder passwordEncoder) {
        this.userRepositoryGateway = userRepositoryGateway;
        this.emailGateway = emailGateway;
        this.passwordEncoder = passwordEncoder;
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

    public void changePassword(String token, PasswordRecoveryRequest request) throws BusinessException {
        User user = userRepositoryGateway.findByToken(token)
                .orElseThrow(() -> new BusinessException("User not found."));

        if (request.newPassword().isBlank())
            throw new BusinessException("New Password cannot be empty.");

        if (request.newPasswordConfirmation().isBlank())
            throw new BusinessException("New Password confirmation cannot be empty.");

        if (user.getTokenExpiration().isBefore(LocalDateTime.now())) {
            throw new BusinessException("Token has expired.");
        }

        if (!request.newPassword().equals(request.newPasswordConfirmation()))
            throw new BusinessException("Passwords don't match.");

        String encodedPassword = passwordEncoder.encode(request.newPassword());
        user.setPassword(encodedPassword);
        user.setToken(null);
        user.setTokenExpiration(null);

        userRepositoryGateway.save(user);
    }
}
