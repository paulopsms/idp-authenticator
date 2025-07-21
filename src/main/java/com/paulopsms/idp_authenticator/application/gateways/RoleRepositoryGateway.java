package com.paulopsms.idp_authenticator.application.gateways;

import com.paulopsms.idp_authenticator.domain.entities.user.Role;
import com.paulopsms.idp_authenticator.domain.entities.user.UserRole;

import java.util.Optional;

public interface RoleRepositoryGateway {

    Optional<Role> findByRole(UserRole role);
}
