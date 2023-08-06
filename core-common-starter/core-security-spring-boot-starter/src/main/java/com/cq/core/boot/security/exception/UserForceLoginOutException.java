package com.cq.core.boot.security.exception;


public class UserForceLoginOutException extends BaseAuthenticationException {
    private static final long serialVersionUID = -4475605487849225175L;

    public UserForceLoginOutException(String msg) {
        super(msg);
    }
}
