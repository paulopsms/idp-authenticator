package com.paulopsms.idp_authenticator.application.dto.user;

import com.paulopsms.idp_authenticator.domain.entities.user.Role;

import java.util.List;
import java.util.UUID;

public record UserResponse(UUID id, String username, String email, List<Role> roles) {
}
