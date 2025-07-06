package com.paulopsms.idp_authenticator.application.mappers;

import com.paulopsms.idp_authenticator.application.dto.UserRequest;
import com.paulopsms.idp_authenticator.application.dto.UserResponse;
import com.paulopsms.idp_authenticator.domain.entities.user.User;

public class UserDtoMapper {

    public UserResponse toUserResponse(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }

    public User toModel(UserRequest request) {
        return new User(request.name(), request.email(), request.password());
    }
}
