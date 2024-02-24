package com.backend.dto;

public class SignInDTO {
    private String username;
    private String password;

    public SignInDTO() {
    }

    public SignInDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
