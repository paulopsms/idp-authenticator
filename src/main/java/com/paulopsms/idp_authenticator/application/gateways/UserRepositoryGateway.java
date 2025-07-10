package com.paulopsms.idp_authenticator.application.gateways;

import com.paulopsms.idp_authenticator.domain.entities.user.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryGateway {

    User save(User user);

    Optional<User> findByEmailIgnoreCase(String email);

    Optional<User> findByToken(String token);

    Optional<User> findById(UUID id);
}
