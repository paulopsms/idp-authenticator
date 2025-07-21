package com.paulopsms.idp_authenticator.application.usecases.user;

import com.paulopsms.idp_authenticator.application.gateways.RoleRepositoryGateway;
import com.paulopsms.idp_authenticator.application.gateways.UserRepositoryGateway;
import com.paulopsms.idp_authenticator.domain.entities.user.Role;
import com.paulopsms.idp_authenticator.domain.entities.user.User;
import com.paulopsms.idp_authenticator.domain.entities.user.UserRole;
import com.paulopsms.idp_authenticator.domain.exceptions.BusinessException;

import java.time.LocalDateTime;

public class VerifyAccountUseCase {

    private final UserRepositoryGateway userRepositoryGateway;

    private final RoleRepositoryGateway roleRepositoryGateway;

    public VerifyAccountUseCase(UserRepositoryGateway userRepositoryGateway, RoleRepositoryGateway roleRepositoryGateway) {
        this.userRepositoryGateway = userRepositoryGateway;
        this.roleRepositoryGateway = roleRepositoryGateway;
    }

    public void verifyAccount(String token) throws BusinessException {
        User user = userRepositoryGateway.findByToken(token)
                .orElseThrow(() -> new BusinessException("User not found."));



        user.accountVerification();

        Role role = this.roleRepositoryGateway.findByRole(UserRole.VERIFIED_USER).orElseThrow();
        user.addRole(role);

        userRepositoryGateway.save(user);
    }
}
