package com.example.novindemo.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private String name;
    private String username;
    private String password;
    private String role;

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
