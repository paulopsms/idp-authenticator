package com.paulopsms.idp_authenticator.application.dto;

import com.paulopsms.idp_authenticator.domain.entities.user.UserRoles;

public record UserRequest(String username, String password, String email, UserRoles role) {
}
