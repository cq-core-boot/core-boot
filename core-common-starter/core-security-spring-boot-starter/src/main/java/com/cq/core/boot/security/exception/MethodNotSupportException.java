package com.cq.core.boot.security.exception;


public class MethodNotSupportException extends BaseAuthenticationException {
    private static final long serialVersionUID = 58586010429775838L;

    public MethodNotSupportException(String msg) {
        super(msg);
    }
}
