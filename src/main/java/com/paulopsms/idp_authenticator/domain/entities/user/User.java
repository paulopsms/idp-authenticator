package com.paulopsms.idp_authenticator.domain.entities.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class User {

    private UUID id;
    private String username;
    private String password;
    private String email;
    private String token;
    private LocalDateTime tokenExpiration;
    private UserRoles role;

    public User() {
    }

    public User(String username, String password, String email, UserRoles role) {
        this.username = username;
        this.password = password;
        this.email = email;
//        this.token = UUID.randomUUID().toString();
//        this.tokenExpiration = LocalDateTime.now().plusMinutes(30);
        this.role = role;
    }

//    public User(String username, String password, String email, String token, LocalDateTime tokenExpiration, UserRoles role) {
//        this.username = username;
//        this.password = password;
//        this.email = email;
//        this.token = token;
//        this.tokenExpiration = tokenExpiration;
//        this.role = role;
//    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
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

    public UserRoles getRole() {
        return role;
    }
}
