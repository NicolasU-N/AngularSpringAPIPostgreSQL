package com.example.demo.configurations.security.JWT;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

public class JwtRequest {

    @NonNull
    @NotEmpty
    @Size(min = 3, max = 30)
    private String username;

    @NonNull
    @NotEmpty
    @Size(min = 5, max = 100)
    private String password;

    public JwtRequest() {
    }

    public JwtRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    @Override
    public String toString() {
        return "{" +
            " username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            "}";
    }
    
}