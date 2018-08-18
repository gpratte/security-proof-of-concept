package com.example.poc.security.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class Token3 extends UsernamePasswordAuthenticationToken {
    public Token3(Object principal, Object credentials) {
        super(principal, credentials);
    }
}
