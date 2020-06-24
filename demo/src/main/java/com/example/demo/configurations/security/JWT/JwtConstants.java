package com.example.demo.configurations.security.JWT;

import java.security.Key;

import org.springframework.http.HttpHeaders;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtConstants {

    public static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public static final String HEADER = HttpHeaders.AUTHORIZATION;
    public static final String TOKEN_HEADER = "Bearer";
    
}