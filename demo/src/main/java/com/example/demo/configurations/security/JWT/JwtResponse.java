package com.example.demo.configurations.security.JWT;

public class JwtResponse {

    private final String jwt;


    public JwtResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return this.jwt;
    }

    @Override
    public String toString() {
        return "{" +
            " jwt='" + getJwt() + "'" +
            "}";
    }

    
}