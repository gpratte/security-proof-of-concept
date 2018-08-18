package com.example.poc.security.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class Token2 extends UsernamePasswordAuthenticationToken {
    public Token2(Object principal, Object credentials) {
        super(principal, credentials);
    }
}
