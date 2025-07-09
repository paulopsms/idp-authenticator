package com.paulopsms.idp_authenticator.infrastructure.adapters;

import com.paulopsms.idp_authenticator.application.gateways.UserRepositoryGateway;
import com.paulopsms.idp_authenticator.domain.entities.user.User;
import com.paulopsms.idp_authenticator.infrastructure.mappers.UserMapper;
import com.paulopsms.idp_authenticator.infrastructure.persistence.usuario.UserEntity;
import com.paulopsms.idp_authenticator.infrastructure.persistence.usuario.UserRepository;

import java.util.Optional;
import java.util.UUID;

public class UserRepositoryAdapter implements UserRepositoryGateway {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserRepositoryAdapter(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public User save(User user) {
        UserEntity userEntity = this.userMapper.toEntity(user);

        UserEntity savedUser = this.userRepository.save(userEntity);

        return this.userMapper.toModel(savedUser);
    }

    @Override
    public Optional<User> findByEmailIgnoreCase(String email) {
        return this.userRepository.findByEmailIgnoreCase(email).map(userMapper::toModel);
    }

    @Override
    public Optional<User> findByToken(String token) {
        return this.userRepository.findByToken(token).map(userMapper::toModel);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return this.userRepository.findById(id).map(userMapper::toModel);
    }
}
