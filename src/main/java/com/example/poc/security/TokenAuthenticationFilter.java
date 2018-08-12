package com.example.poc.security;

import com.example.poc.security.token.Token1;
import com.example.poc.security.token.Token2;
import com.example.poc.security.token.Token3;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.removeStart;

public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private static final String BEARER = "Bearer";

    TokenAuthenticationFilter(final RequestMatcher requiresAuth) {
        super(requiresAuth);
        System.out.println("\n!!!\n!!!\n!!! TokenAuthenticationFilter " + requiresAuth);
    }

    @Override
    public Authentication attemptAuthentication(
      final HttpServletRequest request,
      final HttpServletResponse response) {
        System.out.println("\n!!!\n!!! attemptAuthentication");
        final String param = ofNullable(request.getHeader("Authorization"))
          .orElse(request.getParameter("t"));

        System.out.println("!!! param " + param);

        if (param == null) {
            System.out.println("param is null");
            throw new BadCredentialsException("Missing Authentication Token");
        }
        System.out.println("param is not null");

        UsernamePasswordAuthenticationToken token = null;

        if (param.startsWith("Tkn1")) {
            final String tokenValue = ofNullable(param)
              .map(value -> removeStart(value, "Tkn1"))
              .map(String::trim)
              .orElseThrow(() -> new BadCredentialsException("Missing Authentication Token"));
            token = new Token1(tokenValue, tokenValue);
        } else if (param.startsWith("Tkn2")) {
            final String tokenValue = ofNullable(param)
              .map(value -> removeStart(value, "Tkn2"))
              .map(String::trim)
              .orElseThrow(() -> new BadCredentialsException("Missing Authentication Token"));
            token = new Token2(tokenValue, tokenValue);
        } else if (param.startsWith("Tkn3")) {
            final String tokenValue = ofNullable(param)
              .map(value -> removeStart(value, "Tkn3"))
              .map(String::trim)
              .orElseThrow(() -> new BadCredentialsException("Missing Authentication Token"));
            token = new Token3(tokenValue, tokenValue);
        } else {
            throw new BadCredentialsException("Unknown Authentication Token");
        }

        System.out.println("!!! token " + token);
        return getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(
      final HttpServletRequest request,
      final HttpServletResponse response,
      final FilterChain chain,
      final Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        System.out.println("!!! successfulAuthentication");
        chain.doFilter(request, response);
    }
}