package com.paulopsms.idp_authenticator.application.usecases.user;

import com.paulopsms.idp_authenticator.application.dto.user.UserRequest;
import com.paulopsms.idp_authenticator.application.dto.user.UserResponse;
import com.paulopsms.idp_authenticator.application.gateways.RoleRepositoryGateway;
import com.paulopsms.idp_authenticator.application.gateways.UserRepositoryGateway;
import com.paulopsms.idp_authenticator.application.gateways.VerifyUserAccountEmailGateway;
import com.paulopsms.idp_authenticator.application.mappers.UserDtoMapper;
import com.paulopsms.idp_authenticator.domain.entities.user.Role;
import com.paulopsms.idp_authenticator.domain.entities.user.User;
import com.paulopsms.idp_authenticator.domain.entities.user.UserRole;
import com.paulopsms.idp_authenticator.domain.exceptions.BusinessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

public class SaveUserUseCase {

    private final UserRepositoryGateway userRepositoryGateway;

    private final UserDtoMapper userDtoMapper;

    private final PasswordEncoder passwordEncoder;

    private final VerifyUserAccountEmailGateway verifyUserAccountEmailGateway;

    private final RoleRepositoryGateway roleRepositoryGateway;

    public SaveUserUseCase(UserRepositoryGateway userRepositoryGateway, UserDtoMapper userDtoMapper, PasswordEncoder passwordEncoder, VerifyUserAccountEmailGateway verifyUserAccountEmailGateway, RoleRepositoryGateway roleRepositoryGateway) {
        this.userRepositoryGateway = userRepositoryGateway;
        this.userDtoMapper = userDtoMapper;
        this.passwordEncoder = passwordEncoder;
        this.verifyUserAccountEmailGateway = verifyUserAccountEmailGateway;
        this.roleRepositoryGateway = roleRepositoryGateway;
    }

    @Transactional
    public UserResponse saveUser(UserRequest request) throws BusinessException {
        User user = this.userDtoMapper.toModel(request);

        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        Role role = this.roleRepositoryGateway.findByRole(UserRole.FRESH_USER).orElseThrow();
        user.addRole(role);

        User savedUser = this.userRepositoryGateway.save(user);

        this.verifyUserAccountEmailGateway.sendEmail(user);

        return this.userDtoMapper.toUserResponse(savedUser);
    }
}
