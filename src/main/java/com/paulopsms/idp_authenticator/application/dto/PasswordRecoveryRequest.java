package com.paulopsms.idp_authenticator.application.dto;

public record PasswordRecoveryRequest(String newPassword, String newPasswordConfirmation){
}
