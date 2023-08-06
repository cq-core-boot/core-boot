package com.cq.core.boot.commons.exception;


import com.cq.core.boot.commons.constants.BaseEnum;
import lombok.Getter;

/**
 * @author cqmike 强制业务异常必须提供code码，便于统一维护
 */
@Getter
public class BusinessException extends BaseException {

    private static final long serialVersionUID = -7395726866529812140L;
    private final BaseEnum msg;
    private Object data;

    public BusinessException(BaseEnum msg) {
        super(msg);
        this.msg = msg;
    }

    public BusinessException(BaseEnum msg, Object data) {
        super(msg);
        this.msg = msg;
        this.data = data;
    }

    public BusinessException(BaseEnum msg, Object data, Throwable throwable) {
        super(msg, throwable);
        this.msg = msg;
        this.data = data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
