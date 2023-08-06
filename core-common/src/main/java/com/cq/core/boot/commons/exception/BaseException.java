package com.cq.core.boot.commons.exception;

import com.cq.core.boot.commons.constants.BaseEnum;
import com.cq.core.boot.commons.constants.CodeEnum;
import lombok.Getter;

/**
 * @author <a href="mailto:cqmike0315@gmail.com">chenqi</a>
 * @version 1.0
 */
@Getter
public abstract class BaseException extends RuntimeException {
    private static final long serialVersionUID = -467193070103185483L;

    private final BaseEnum msg;

    protected BaseException(String message) {
        super(message);
        this.msg = CodeEnum.SystemError;
    }

    protected BaseException(BaseEnum msg) {
        super(msg.getName());
        this.msg = msg;
    }

    protected BaseException(BaseEnum msg, Throwable throwable) {
        super(msg.getName(), throwable);
        this.msg = msg;
    }
}
