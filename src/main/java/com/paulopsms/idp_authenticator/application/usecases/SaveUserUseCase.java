package com.paulopsms.idp_authenticator.application.usecases;

import com.paulopsms.idp_authenticator.application.dto.UserRequest;
import com.paulopsms.idp_authenticator.application.dto.UserResponse;
import com.paulopsms.idp_authenticator.application.gateways.UserRepositoryGateway;
import com.paulopsms.idp_authenticator.application.mappers.UserDtoMapper;
import com.paulopsms.idp_authenticator.domain.entities.user.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SaveUserUseCase {

    private final UserRepositoryGateway userRepositoryGateway;

    private final UserDtoMapper userDtoMapper;

    private final PasswordEncoder passwordEncoder;

    public SaveUserUseCase(UserRepositoryGateway userRepositoryGateway, UserDtoMapper userDtoMapper, PasswordEncoder passwordEncoder) {
        this.userRepositoryGateway = userRepositoryGateway;
        this.userDtoMapper = userDtoMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse saveUser(UserRequest request) {
        User user = this.userDtoMapper.toModel(request);

        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        User savedUser = this.userRepositoryGateway.save(user);

        // TODO implement confirmation e-mail sending to activate user

        return this.userDtoMapper.toUserResponse(savedUser);
    }
}
