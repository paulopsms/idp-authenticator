package com.paulopsms.idp_authenticator.infrastructure.controllers;

import com.paulopsms.idp_authenticator.application.dto.SendTokenRequest;
import com.paulopsms.idp_authenticator.application.dto.UserRequest;
import com.paulopsms.idp_authenticator.application.dto.UserResponse;
import com.paulopsms.idp_authenticator.application.exceptions.BusinessException;
import com.paulopsms.idp_authenticator.application.usecases.SaveUserUseCase;
import com.paulopsms.idp_authenticator.application.usecases.SendTokenUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final SaveUserUseCase saveUserUseCase;

    private final SendTokenUseCase sendTokenUseCase;

    public UserController(SaveUserUseCase saveUserUseCase, SendTokenUseCase sendTokenUseCase) {
        this.saveUserUseCase = saveUserUseCase;
        this.sendTokenUseCase = sendTokenUseCase;
    }

    @PostMapping
    public ResponseEntity<UserResponse> saveUser(UserRequest request) {
        UserResponse userResponse = this.saveUserUseCase.saveUser(request);

        return ResponseEntity.ok(userResponse);
    }

    @GetMapping
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> listAllUsers() {
        List<UserResponse> users = Collections.emptyList();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(SendTokenRequest request) throws BusinessException {
        this.sendTokenUseCase.sendToken(request);
        return ResponseEntity.ok("An email has been sent to your email address.");
    }
}
