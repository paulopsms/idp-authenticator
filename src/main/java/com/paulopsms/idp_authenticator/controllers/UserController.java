package com.paulopsms.idp_authenticator.controllers;

import com.paulopsms.idp_authenticator.application.dto.user.PasswordRecoveryRequest;
import com.paulopsms.idp_authenticator.application.dto.user.ForgotPasswordRequest;
import com.paulopsms.idp_authenticator.application.dto.user.UserRequest;
import com.paulopsms.idp_authenticator.application.dto.user.UserResponse;
import com.paulopsms.idp_authenticator.application.usecases.user.SaveUserUseCase;
import com.paulopsms.idp_authenticator.application.usecases.user.RecoveryUserPasswordUseCase;
import com.paulopsms.idp_authenticator.domain.exceptions.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final SaveUserUseCase saveUserUseCase;

    private final RecoveryUserPasswordUseCase recoveryUserPasswordUseCase;

    public UserController(SaveUserUseCase saveUserUseCase, RecoveryUserPasswordUseCase recoveryUserPasswordUseCase) {
        this.saveUserUseCase = saveUserUseCase;
        this.recoveryUserPasswordUseCase = recoveryUserPasswordUseCase;
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
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) throws BusinessException {
        this.recoveryUserPasswordUseCase.sendToken(request);
        return ResponseEntity.ok("An email has been sent to your email address.");
    }

//    @GetMapping("/password-recovery")
//    public ResponseEntity<String> forgotPassword(@RequestParam("token") String token) throws BusinessException {
////        this.sendTokenUseCase.changePassword(token);
//        return ResponseEntity.ok("Your password has been updated successfully.");
//    }

    @PostMapping("/password-recovery")
    public ResponseEntity<String> updatePassword(@RequestParam("token") String token, @RequestBody PasswordRecoveryRequest request) throws BusinessException {
        this.recoveryUserPasswordUseCase.changePassword(token, request);

        return ResponseEntity.ok("Your password has been updated successfully.");
    }
}
