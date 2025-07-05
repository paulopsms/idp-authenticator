package com.paulopsms.idp_authenticator.application.dto;

import com.paulopsms.idp_authenticator.domain.entities.user.UserRoles;

import java.util.UUID;

public record UserResponse (UUID id, String username, String email, UserRoles role) {
}
