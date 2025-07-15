package com.paulopsms.idp_authenticator.infrastructure.configuration;

import com.paulopsms.idp_authenticator.application.gateways.*;
import com.paulopsms.idp_authenticator.application.mappers.UserDtoMapper;
import com.paulopsms.idp_authenticator.application.usecases.login.LoggedInUserUseCase;
import com.paulopsms.idp_authenticator.application.usecases.login.LoginUseCase;
import com.paulopsms.idp_authenticator.application.usecases.login.RefreshTokenUseCase;
import com.paulopsms.idp_authenticator.application.usecases.user.SaveUserUseCase;
import com.paulopsms.idp_authenticator.application.usecases.user.RecoveryUserPasswordUseCase;
import com.paulopsms.idp_authenticator.application.usecases.user.VerifyAccountUseCase;
import com.paulopsms.idp_authenticator.infrastructure.adapters.login.AuthenticationAdapter;
import com.paulopsms.idp_authenticator.infrastructure.adapters.login.JwtAdapter;
import com.paulopsms.idp_authenticator.infrastructure.adapters.login.LoggedInUserAdapter;
import com.paulopsms.idp_authenticator.infrastructure.adapters.login.RefreshTokenAdapter;
import com.paulopsms.idp_authenticator.infrastructure.adapters.user.ForgotPasswordEmailAdapter;
import com.paulopsms.idp_authenticator.infrastructure.adapters.user.UserRepositoryAdapter;
import com.paulopsms.idp_authenticator.infrastructure.adapters.user.VerifyUserAccountEmailAdapter;
import com.paulopsms.idp_authenticator.infrastructure.mappers.UserMapper;
import com.paulopsms.idp_authenticator.infrastructure.persistence.usuario.UserRepository;
import com.paulopsms.idp_authenticator.infrastructure.service.EmailService;
import com.paulopsms.idp_authenticator.infrastructure.service.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Config {

    @Bean
    public SaveUserUseCase createSaveUserUseCase(UserRepositoryGateway userRepositoryGateway, UserDtoMapper userDtoMapper, PasswordEncoder passwordEncoder, VerifyUserAccountEmailGateway verifyUserAccountEmailGateway) {
        return new SaveUserUseCase(userRepositoryGateway, userDtoMapper, passwordEncoder, verifyUserAccountEmailGateway);
    }

    @Bean
    public RecoveryUserPasswordUseCase createSendTokenUseCase(UserRepositoryGateway userRepositoryGateway, ForgotPasswordEmailGateway emailGateway, PasswordEncoder passwordEncoder) {
        return new RecoveryUserPasswordUseCase(userRepositoryGateway, emailGateway, passwordEncoder);
    }

    @Bean
    public ForgotPasswordEmailGateway createForgotPasswordEmailGateway(EmailService emailService) {
        return new ForgotPasswordEmailAdapter(emailService);
    }

    @Bean
    public VerifyUserAccountEmailGateway createVerifyUserEmailGateway(EmailService emailService) {
        return new VerifyUserAccountEmailAdapter(emailService);
    }

    @Bean
    public EmailService createEmailService(JavaMailSender javaMailSender) {
        return new EmailService(javaMailSender);
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

    @Bean
    public RefreshTokenUseCase createRefreshTokenUseCase(RefreshTokenGateway refreshTokenGateway, UserRepositoryGateway userRepositoryGateway, JwtGateway jwtGateway) {
        return new RefreshTokenUseCase(refreshTokenGateway, userRepositoryGateway, jwtGateway);
    }

    @Bean
    public RefreshTokenGateway createRefreshTokenGateway(JwtService jwtService) {
        return new RefreshTokenAdapter(jwtService);
    }

    @Bean
    public LoggedInUserUseCase createLoggedInUserUseCase(LoggedInUserGateway loggedInUserGateway, UserDtoMapper userDtoMapper) {
        return new LoggedInUserUseCase(loggedInUserGateway, userDtoMapper);
    }

    @Bean
    public LoggedInUserGateway createLoggedInUserGateway(UserMapper userMapper) {
        return new LoggedInUserAdapter(userMapper);
    }

    @Bean
    public VerifyAccountUseCase createVerifyAccountUseCase(UserRepositoryGateway userRepositoryGateway) {
        return new VerifyAccountUseCase(userRepositoryGateway);
    }
}
