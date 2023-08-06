package com.cq.core.boot.commons.exception;

import lombok.Getter;

/**
 * @author cqmike
 **/
@Getter
public class SystemException extends BaseException {

    private static final long serialVersionUID = -6049220729887363535L;

    private String msg;

    public SystemException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
