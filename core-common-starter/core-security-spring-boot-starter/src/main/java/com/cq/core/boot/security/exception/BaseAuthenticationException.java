package com.cq.core.boot.security.exception;

import com.cq.core.boot.commons.constants.BaseEnum;
import com.cq.core.boot.commons.constants.CodeEnum;
import org.springframework.security.core.AuthenticationException;

/**
 * @author <a href="mailto:cqmike0315@gmail.com">chenqi</a>
 * @version 1.0
 */
public abstract class BaseAuthenticationException extends AuthenticationException {
    private static final long serialVersionUID = 6114199694085710473L;

    private final BaseEnum baseEnum;

    public BaseAuthenticationException(String msg, Throwable t) {
        super(msg, t);
        this.baseEnum = CodeEnum.SystemError;
    }

    public BaseAuthenticationException(String msg) {
        super(msg);
        this.baseEnum = CodeEnum.SystemError;
    }

    public BaseAuthenticationException(BaseEnum baseEnum) {
        super(baseEnum.getName());
        this.baseEnum = baseEnum;
    }

    public BaseAuthenticationException(BaseEnum baseEnum, Throwable t) {
        super(baseEnum.getName(), t);
        this.baseEnum = baseEnum;
    }
}
