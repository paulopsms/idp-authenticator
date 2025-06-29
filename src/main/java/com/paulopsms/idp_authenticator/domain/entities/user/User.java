package com.paulopsms.idp_authenticator.domain.entities.user;

import java.util.UUID;

public class User {

    private UUID id;
    private String username;
    private String password;
    private String role;

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
