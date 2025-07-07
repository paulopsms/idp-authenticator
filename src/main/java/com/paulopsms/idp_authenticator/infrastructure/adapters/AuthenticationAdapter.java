package com.paulopsms.idp_authenticator.infrastructure.adapters;

import com.paulopsms.idp_authenticator.application.gateways.AuthenticationGateway;
import com.paulopsms.idp_authenticator.domain.entities.user.User;
import com.paulopsms.idp_authenticator.infrastructure.mappers.UserMapper;
import com.paulopsms.idp_authenticator.infrastructure.persistence.usuario.UserEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class AuthenticationAdapter implements AuthenticationGateway {

    private final AuthenticationManager authenticationManager;

    private final UserMapper userMapper;

    public AuthenticationAdapter(AuthenticationManager authenticationManager, UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
    }

    @Override
    public User authenticate(String username, String password) {
        var authToken = new UsernamePasswordAuthenticationToken(username, password);

        Authentication authentication = this.authenticationManager.authenticate(authToken);

        UserEntity userEntity = (UserEntity) authentication.getPrincipal();

        return this.userMapper.toModel(userEntity);
    }
}
