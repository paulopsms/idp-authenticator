package com.paulopsms.idp_authenticator.infrastructure.gateways;

import com.paulopsms.idp_authenticator.application.gateways.UserRepository;
import com.paulopsms.idp_authenticator.domain.entities.user.User;
import com.paulopsms.idp_authenticator.infrastructure.mappers.UserMapper;
import com.paulopsms.idp_authenticator.infrastructure.persistence.usuario.UserEntity;
import com.paulopsms.idp_authenticator.infrastructure.persistence.usuario.UserJpaRepository;

import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    private final UserMapper userMapper;

    public UserRepositoryImpl(UserJpaRepository userJpaRepository, UserMapper userMapper) {
        this.userJpaRepository = userJpaRepository;
        this.userMapper = userMapper;
    }


    @Override
    public User save(User user) {
        UserEntity userEntity = this.userMapper.toEntity(user);
        UserEntity savedUser = this.userJpaRepository.save(userEntity);
        return this.userMapper.toModel(savedUser);
    }

    @Override
    public Optional<User> findByEmailIgnoreCase(String email) {
        return this.userJpaRepository.findByEmailIgnoreCase(email).map(userMapper::toModel);
    }
}
