package com.paulopsms.idp_authenticator.infrastructure.controllers;

import com.paulopsms.idp_authenticator.application.dto.PasswordRecoveryRequest;
import com.paulopsms.idp_authenticator.application.dto.SendTokenRequest;
import com.paulopsms.idp_authenticator.application.dto.UserRequest;
import com.paulopsms.idp_authenticator.application.dto.UserResponse;
import com.paulopsms.idp_authenticator.application.usecases.SaveUserUseCase;
import com.paulopsms.idp_authenticator.application.usecases.SendTokenUseCase;
import com.paulopsms.idp_authenticator.domain.exceptions.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<UserResponse> saveUser(@RequestBody UserRequest request) {
        UserResponse userResponse = this.saveUserUseCase.saveUser(request);

        return ResponseEntity.ok(userResponse);
    }

    @GetMapping
//    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> listAllUsers() {
        List<UserResponse> users = Collections.emptyList();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody SendTokenRequest request) throws BusinessException {
        this.sendTokenUseCase.sendToken(request);
        return ResponseEntity.ok("An email has been sent to your email address.");
    }

    @GetMapping("/change-password")
    public ResponseEntity<String> forgotPassword(@RequestParam("token") String token) throws BusinessException {
//        this.sendTokenUseCase.changePassword(token);
        return ResponseEntity.ok("Your password has been updated successfully.");
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> updatePassword(@RequestParam("token") String token, PasswordRecoveryRequest request) throws BusinessException {
//        this.sendTokenUseCase.changePassword(token);
        return ResponseEntity.ok("Your password has been updated successfully.");
    }
}
