package com.example.poc.security.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class Token1 extends UsernamePasswordAuthenticationToken {
    public Token1(Object principal, Object credentials) {
        super(principal, credentials);
    }
}
