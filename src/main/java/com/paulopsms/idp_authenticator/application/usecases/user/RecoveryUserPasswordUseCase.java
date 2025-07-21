package com.paulopsms.idp_authenticator.application.usecases.user;

import com.paulopsms.idp_authenticator.application.dto.user.PasswordRecoveryRequest;
import com.paulopsms.idp_authenticator.application.dto.user.ForgotPasswordRequest;
import com.paulopsms.idp_authenticator.application.gateways.ForgotPasswordEmailGateway;
import com.paulopsms.idp_authenticator.domain.exceptions.BusinessException;
import com.paulopsms.idp_authenticator.application.gateways.UserRepositoryGateway;
import com.paulopsms.idp_authenticator.domain.entities.user.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.UUID;

public class RecoveryUserPasswordUseCase {

    private final UserRepositoryGateway userRepositoryGateway;

    private final ForgotPasswordEmailGateway forgotPasswordEmailGateway;

    private final PasswordEncoder passwordEncoder;

    public RecoveryUserPasswordUseCase(UserRepositoryGateway userRepositoryGateway, ForgotPasswordEmailGateway forgotPasswordEmailGateway, PasswordEncoder passwordEncoder) {
        this.userRepositoryGateway = userRepositoryGateway;
        this.forgotPasswordEmailGateway = forgotPasswordEmailGateway;
        this.passwordEncoder = passwordEncoder;
    }

    public void sendToken(ForgotPasswordRequest request) throws BusinessException {
        User user = userRepositoryGateway.findByEmailIgnoreCase(request.email())
                .orElseThrow(() -> new BusinessException("User not found."));

        String token = UUID.randomUUID().toString();
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(30);

        user.setToken(token);
        user.setTokenExpiration(expirationTime);

        userRepositoryGateway.save(user);
        this.forgotPasswordEmailGateway.sendEmail(user);
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
        user.setVerified(true);

        userRepositoryGateway.save(user);
    }
}
