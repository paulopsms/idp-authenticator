package com.paulopsms.idp_authenticator.application.usecases;

import com.paulopsms.idp_authenticator.application.dto.user.UserResponse;
import com.paulopsms.idp_authenticator.application.gateways.LoggedInUserGateway;
import com.paulopsms.idp_authenticator.application.mappers.UserDtoMapper;
import com.paulopsms.idp_authenticator.domain.entities.user.User;

public class LoggedInUserUseCase {

    private final LoggedInUserGateway loggedInUserGateway;

    private final UserDtoMapper userDtoMapper;

    public LoggedInUserUseCase(LoggedInUserGateway loggedInUserGateway, UserDtoMapper userDtoMapper) {
        this.loggedInUserGateway = loggedInUserGateway;
        this.userDtoMapper = userDtoMapper;
    }


    public UserResponse getLoggedInUser() {
        User loggedInUser = this.loggedInUserGateway.getLoggedInUser();

        return this.userDtoMapper.toUserResponse(loggedInUser);
    }
}
