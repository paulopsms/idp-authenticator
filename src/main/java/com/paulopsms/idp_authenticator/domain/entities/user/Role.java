package com.paulopsms.idp_authenticator.domain.entities.user;

public class Role {

    private Long id;

    private UserRole role;

    public Role() {
    }

    public Role(UserRole role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserRole getRole() {
        return this.role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
