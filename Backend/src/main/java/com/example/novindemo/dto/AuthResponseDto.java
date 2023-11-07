package com.example.novindemo.dto;


import com.example.novindemo.entity.Role;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
public class AuthResponseDto {
    String token;
    Set<Role> roles;
    Long userId;
    Date loginDate;
    String name;

    public AuthResponseDto(String token, Set<Role> roles, Long userId, Date loginDate,String name) {
        this.token = token;
        this.roles = roles;
        this.userId = userId;
        this.loginDate = loginDate;
        this.name = name;
    }

    public AuthResponseDto(String token, Set<Role> roles) {
        this.token = token;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public Long getUserId() {
        return userId;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getToken() {
        return token;
    }
}
