package com.paulopsms.idp_authenticator.infrastructure.configuration;

import com.paulopsms.idp_authenticator.application.gateways.EmailGateway;
import com.paulopsms.idp_authenticator.application.gateways.UserRepositoryGateway;
import com.paulopsms.idp_authenticator.application.mappers.UserDtoMapper;
import com.paulopsms.idp_authenticator.application.usecases.SaveUserUseCase;
import com.paulopsms.idp_authenticator.application.usecases.SendTokenUseCase;
import com.paulopsms.idp_authenticator.infrastructure.adapters.EmailAdapter;
import com.paulopsms.idp_authenticator.infrastructure.adapters.UserRepositoryAdapter;
import com.paulopsms.idp_authenticator.infrastructure.mappers.UserMapper;
import com.paulopsms.idp_authenticator.infrastructure.persistence.usuario.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Config {

    @Bean
    public SaveUserUseCase createSaveUserUseCase(UserRepositoryGateway userRepositoryGateway, UserDtoMapper userDtoMapper, PasswordEncoder passwordEncoder) {
        return new SaveUserUseCase(userRepositoryGateway, userDtoMapper, passwordEncoder);
    }

    @Bean
    public SendTokenUseCase createSendTokenUseCase(UserRepositoryGateway userRepositoryGateway, EmailGateway emailGateway) {
        return new SendTokenUseCase(userRepositoryGateway, emailGateway);
    }

    @Bean
    public EmailGateway createEmailGateway(JavaMailSender javaMailSender) {
        return new EmailAdapter(javaMailSender);
    }

    @Bean
    public UserRepositoryAdapter createUserRepositoryImpl(UserRepository userRepository, UserMapper userMapper) {
        return new UserRepositoryAdapter(userRepository, userMapper);
    }

    @Bean
    public UserDtoMapper createUserDtoMapper() {
        return new UserDtoMapper();
    }
}
