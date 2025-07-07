package com.paulopsms.idp_authenticator.application.dto.user;

public record PasswordRecoveryRequest(String newPassword, String newPasswordConfirmation){
}
