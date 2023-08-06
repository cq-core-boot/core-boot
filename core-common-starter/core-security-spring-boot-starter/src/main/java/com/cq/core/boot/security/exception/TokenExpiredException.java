package com.cq.core.boot.security.exception;


public class TokenExpiredException extends BaseAuthenticationException {
    private static final long serialVersionUID = 2228174799850599873L;

    public TokenExpiredException(String msg) {
        super(msg);
    }
}
