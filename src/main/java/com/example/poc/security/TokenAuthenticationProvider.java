package com.example.poc.security;

import com.example.poc.security.token.Token1;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("!!! authenticate name: " + authentication.getName() + " credentials: " + authentication.getCredentials());

        Set<GrantedAuthority> authorities = remoteAuthenticate((String) authentication.getPrincipal());

        Authentication auth = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), authorities);
        System.out.println("!!! authenticate returning " + auth);
        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        switch (authentication.getName()) {
            case "com.example.poc.security.token.Token1":
            case "com.example.poc.security.token.Token2":
            case "com.example.poc.security.token.Token3":
                return true;
            default:
                return false;
        }
    }

    private Set<GrantedAuthority> remoteAuthenticate(String token) {
        Set<GrantedAuthority> authorities = new HashSet<>();

        switch (token) {
            case "12345":
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                authorities.add(new SimpleGrantedAuthority("ROLE_TKN1"));
                break;
            case "23456":
                authorities.add(new SimpleGrantedAuthority("ROLE_SUPER_USER"));
                authorities.add(new SimpleGrantedAuthority("ROLE_TKN1"));
                break;
            case "34567":
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                authorities.add(new SimpleGrantedAuthority("ROLE_TKN1"));
                break;
            case "45678":
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                authorities.add(new SimpleGrantedAuthority("ROLE_TKN2"));
                break;
            case "56789":
                authorities.add(new SimpleGrantedAuthority("ROLE_SUPER_USER"));
                authorities.add(new SimpleGrantedAuthority("ROLE_TKN2"));
                break;
            case "67890":
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                authorities.add(new SimpleGrantedAuthority("ROLE_TKN2"));
                break;
            case "78901":
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                authorities.add(new SimpleGrantedAuthority("ROLE_TKN3"));
                break;
            default:
                throw new BadCredentialsException("Invalid Authentication Token");
        }
        return authorities;
    }
}
