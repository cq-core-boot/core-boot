package com.cq.core.boot.security.exception;


public class IllegalTokenException extends BaseAuthenticationException {
    private static final long serialVersionUID = -2787650281090337441L;

    public IllegalTokenException(String msg) {
        super(msg);
    }
}
