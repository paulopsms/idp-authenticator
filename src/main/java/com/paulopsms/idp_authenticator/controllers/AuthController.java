package com.paulopsms.idp_authenticator.controllers;

import com.paulopsms.idp_authenticator.application.dto.login.LoginRequest;
import com.paulopsms.idp_authenticator.application.usecases.LoginUseCase;
import com.paulopsms.idp_authenticator.domain.exceptions.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final LoginUseCase loginUseCase;

    public AuthController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) throws BusinessException {
        String token = this.loginUseCase.login(loginRequest);

        return ResponseEntity.ok(token);
    }
}
