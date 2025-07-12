package com.paulopsms.idp_authenticator.infrastructure.adapters;

import com.paulopsms.idp_authenticator.application.gateways.LoggedInUserGateway;
import com.paulopsms.idp_authenticator.domain.entities.user.User;
import com.paulopsms.idp_authenticator.infrastructure.mappers.UserMapper;
import com.paulopsms.idp_authenticator.infrastructure.persistence.usuario.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoggedInUserAdapter implements LoggedInUserGateway {

    private final UserMapper userMapper;

    public LoggedInUserAdapter(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserEntity userEntity = (UserEntity) authentication.getPrincipal();

        return this.userMapper.toModel(userEntity);
    }
}
