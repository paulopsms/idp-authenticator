package com.paulopsms.idp_authenticator.application.gateways;

import com.paulopsms.idp_authenticator.domain.entities.user.User;

import java.util.Optional;

public interface UserRepositoryGateway {

    User save(User user);

    Optional<User> findByEmailIgnoreCase(String email);
}
