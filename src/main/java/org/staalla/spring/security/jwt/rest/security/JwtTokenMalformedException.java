package org.staalla.spring.security.jwt.rest.security;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenMalformedException extends AuthenticationException {


    public JwtTokenMalformedException(String msg) {
        super(msg);
    }
}