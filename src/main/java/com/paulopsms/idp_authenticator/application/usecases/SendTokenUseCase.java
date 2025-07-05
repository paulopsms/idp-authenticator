package com.paulopsms.idp_authenticator.application.usecases;

import com.paulopsms.idp_authenticator.application.dto.SendTokenRequest;
import com.paulopsms.idp_authenticator.application.exceptions.BusinessException;
import com.paulopsms.idp_authenticator.application.gateways.UserRepository;
import com.paulopsms.idp_authenticator.domain.entities.user.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class SendTokenUseCase {

    private final UserRepository userRepository;

    public SendTokenUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void sendToken(SendTokenRequest request) throws BusinessException {
        User user = userRepository.findByEmailIgnoreCase(request.email())
                .orElseThrow(() -> new BusinessException("User not found."));

        String token = UUID.randomUUID().toString();
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(30);

        user.setToken(token);
        user.setTokenExpiration(expirationTime);

        userRepository.save(user);
    }
}
