package com.paulopsms.idp_authenticator.domain.entities.user;

import com.paulopsms.idp_authenticator.domain.exceptions.BusinessException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {

    private UUID id;
    private String name;
    private String email;
    private String password;
    private String token;
    private LocalDateTime tokenExpiration;
    private Boolean verified;
    private List<Role> roles = new ArrayList<>();

    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.token = UUID.randomUUID().toString();
        this.tokenExpiration = LocalDateTime.now().plusMinutes(20);
        this.verified = false;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getTokenExpiration() {
        return tokenExpiration;
    }

    public void setTokenExpiration(LocalDateTime tokenExpiration) {
        this.tokenExpiration = tokenExpiration;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void accountVerification() throws BusinessException {
        if (this.tokenExpiration.isBefore(LocalDateTime.now())) {
            throw new BusinessException("Token has expired.");
        }

        this.token = null;
        this.tokenExpiration = null;
        this.verified = true;
    }
}
