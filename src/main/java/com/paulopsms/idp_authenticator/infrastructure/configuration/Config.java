package com.paulopsms.idp_authenticator.infrastructure.configuration;

import com.paulopsms.idp_authenticator.application.gateways.UserRepository;
import com.paulopsms.idp_authenticator.application.mappers.UserDtoMapper;
import com.paulopsms.idp_authenticator.application.usecases.SaveUserUseCase;
import com.paulopsms.idp_authenticator.application.usecases.SendTokenUseCase;
import com.paulopsms.idp_authenticator.infrastructure.gateways.UserRepositoryImpl;
import com.paulopsms.idp_authenticator.infrastructure.mappers.UserMapper;
import com.paulopsms.idp_authenticator.infrastructure.persistence.usuario.UserJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Config {

    @Bean
    public SaveUserUseCase createSaveUserUseCase(UserRepository userRepository, UserDtoMapper userDtoMapper, PasswordEncoder passwordEncoder) {
        return new SaveUserUseCase(userRepository, userDtoMapper, passwordEncoder);
    }

    @Bean
    public SendTokenUseCase createSendTokenUseCase(UserRepository userRepository) {
        return new SendTokenUseCase(userRepository);
    }

    @Bean
    public UserRepositoryImpl createUserRepositoryImpl(UserJpaRepository userJpaRepository, UserMapper userMapper) {
        return new UserRepositoryImpl(userJpaRepository, userMapper);
    }

    @Bean
    public UserDtoMapper createUserDtoMapper() {
        return new UserDtoMapper();
    }
}
