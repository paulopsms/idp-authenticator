package com.paulopsms.idp_authenticator.controllers;

import com.paulopsms.idp_authenticator.application.dto.user.ForgotPasswordRequest;
import com.paulopsms.idp_authenticator.application.dto.user.PasswordRecoveryRequest;
import com.paulopsms.idp_authenticator.application.dto.user.UserRequest;
import com.paulopsms.idp_authenticator.application.dto.user.UserResponse;
import com.paulopsms.idp_authenticator.application.usecases.user.RecoveryUserPasswordUseCase;
import com.paulopsms.idp_authenticator.application.usecases.user.SaveUserUseCase;
import com.paulopsms.idp_authenticator.application.usecases.user.VerifyAccountUseCase;
import com.paulopsms.idp_authenticator.domain.exceptions.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static com.paulopsms.idp_authenticator.domain.entities.user.UserRole.FRESH_USER;
import static java.util.Arrays.asList;

@RestController
@RequestMapping("/users")
public class UserController {

    private final SaveUserUseCase saveUserUseCase;

    private final RecoveryUserPasswordUseCase recoveryUserPasswordUseCase;

    private final VerifyAccountUseCase verifyAccountUseCase;

    public UserController(SaveUserUseCase saveUserUseCase, RecoveryUserPasswordUseCase recoveryUserPasswordUseCase, VerifyAccountUseCase verifyAccountUseCase) {
        this.saveUserUseCase = saveUserUseCase;
        this.recoveryUserPasswordUseCase = recoveryUserPasswordUseCase;
        this.verifyAccountUseCase = verifyAccountUseCase;
    }

    @PostMapping
    public ResponseEntity<UserResponse> saveUser(@RequestBody UserRequest request) throws BusinessException {
        UserResponse userResponse = this.saveUserUseCase.saveUser(request);

        return ResponseEntity.ok(userResponse);
    }

    @GetMapping
//    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<List<String>> listAllUsers() {
        List<UserResponse> users = Collections.emptyList();
        List<String> list = asList(FRESH_USER.name(), FRESH_USER.toString());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) throws BusinessException {
        this.recoveryUserPasswordUseCase.sendToken(request);
        return ResponseEntity.ok("An email has been sent to your email address.");
    }

    @PostMapping("/password-recovery")
    public ResponseEntity<String> updatePassword(@RequestParam("token") String token, @RequestBody PasswordRecoveryRequest request) throws BusinessException {
        this.recoveryUserPasswordUseCase.changePassword(token, request);

        return ResponseEntity.ok("Your password has been updated successfully.");
    }

    @PostMapping("/verify-account")
    public ResponseEntity<String> verifyAccount(@RequestParam("token") String token) throws BusinessException {
        this.verifyAccountUseCase.verifyAccount(token);

        return ResponseEntity.ok("Your account has been verified successfully.");
    }
}
