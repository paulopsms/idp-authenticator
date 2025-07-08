package com.paulopsms.idp_authenticator.infrastructure.configuration;

import com.paulopsms.idp_authenticator.application.gateways.AuthenticationGateway;
import com.paulopsms.idp_authenticator.application.gateways.EmailGateway;
import com.paulopsms.idp_authenticator.application.gateways.JwtGateway;
import com.paulopsms.idp_authenticator.application.gateways.UserRepositoryGateway;
import com.paulopsms.idp_authenticator.application.mappers.UserDtoMapper;
import com.paulopsms.idp_authenticator.application.usecases.LoginUseCase;
import com.paulopsms.idp_authenticator.application.usecases.user.SaveUserUseCase;
import com.paulopsms.idp_authenticator.application.usecases.user.RecoveryUserPasswordUseCase;
import com.paulopsms.idp_authenticator.infrastructure.adapters.AuthenticationAdapter;
import com.paulopsms.idp_authenticator.infrastructure.adapters.EmailAdapter;
import com.paulopsms.idp_authenticator.infrastructure.adapters.JwtAdapter;
import com.paulopsms.idp_authenticator.infrastructure.adapters.UserRepositoryAdapter;
import com.paulopsms.idp_authenticator.infrastructure.mappers.UserMapper;
import com.paulopsms.idp_authenticator.infrastructure.persistence.usuario.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Config {

    @Bean
    public SaveUserUseCase createSaveUserUseCase(UserRepositoryGateway userRepositoryGateway, UserDtoMapper userDtoMapper, PasswordEncoder passwordEncoder) {
        return new SaveUserUseCase(userRepositoryGateway, userDtoMapper, passwordEncoder);
    }

    @Bean
    public RecoveryUserPasswordUseCase createSendTokenUseCase(UserRepositoryGateway userRepositoryGateway, EmailGateway emailGateway, PasswordEncoder passwordEncoder) {
        return new RecoveryUserPasswordUseCase(userRepositoryGateway, emailGateway, passwordEncoder);
    }

    @Bean
    public EmailGateway createEmailGateway(JavaMailSender javaMailSender) {
        return new EmailAdapter(javaMailSender);
    }

    @Bean
    public LoginUseCase createLoginUseCase(AuthenticationGateway authenticationGateway, JwtGateway jwtGateway) {
        return new LoginUseCase(authenticationGateway, jwtGateway);
    }

    @Bean
    public AuthenticationGateway createAuthenticationGateway(AuthenticationManager authenticationManager, UserMapper userMapper) {
       return new AuthenticationAdapter(authenticationManager, userMapper);
    }

    @Bean
    public JwtAdapter createJwtAdapter() {
        return new JwtAdapter();
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
