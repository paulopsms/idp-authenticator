package com.paulopsms.idp_authenticator.controllers;

import com.paulopsms.idp_authenticator.application.dto.RefreshTokenRequest;
import com.paulopsms.idp_authenticator.application.dto.TokenResponse;
import com.paulopsms.idp_authenticator.application.dto.login.LoginRequest;
import com.paulopsms.idp_authenticator.application.dto.user.UserResponse;
import com.paulopsms.idp_authenticator.application.usecases.LoggedInUserUseCase;
import com.paulopsms.idp_authenticator.application.usecases.LoginUseCase;
import com.paulopsms.idp_authenticator.application.usecases.RefreshTokenUseCase;
import com.paulopsms.idp_authenticator.domain.entities.user.User;
import com.paulopsms.idp_authenticator.domain.exceptions.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final LoginUseCase loginUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;
    private final LoggedInUserUseCase loggedInUserUseCase;

    public AuthController(LoginUseCase loginUseCase, RefreshTokenUseCase refreshTokenUseCase, LoggedInUserUseCase loggedInUserUseCase) {
        this.loginUseCase = loginUseCase;
        this.refreshTokenUseCase = refreshTokenUseCase;
        this.loggedInUserUseCase = loggedInUserUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) throws BusinessException {
        TokenResponse token = this.loginUseCase.login(loginRequest);

        return ResponseEntity.ok(token);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<TokenResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) throws BusinessException {
        TokenResponse token = this.refreshTokenUseCase.refreshToken(refreshTokenRequest);

        return ResponseEntity.ok(token);
    }

    @GetMapping("get-logged-user")
    public ResponseEntity<UserResponse> getLoggedUser() {
        UserResponse loggedInUserResponse = this.loggedInUserUseCase.getLoggedInUser();

        return ResponseEntity.ok(loggedInUserResponse);
    }
}
