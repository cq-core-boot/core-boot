package com.cq.core.boot.commons.model;

import com.cq.core.boot.commons.constants.BaseEnum;
import com.cq.core.boot.commons.constants.CodeEnum;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.Objects;

/**
 * @author cqmike
 **/
@Data
public final class R<E> {

    @Setter(AccessLevel.PRIVATE)
    private Integer code;
    @Setter(AccessLevel.PRIVATE)
    private String msg;
    @Setter(AccessLevel.PRIVATE)
    private E result;
    @Setter(AccessLevel.PRIVATE)
    private long timestamp;

    private R() {
        this.timestamp = System.currentTimeMillis();
    }

    public static <E> R<E> success(E e) {
        R<E> object = new R<>();
        object.setCode(CodeEnum.Success.getCode());
        object.setMsg(CodeEnum.Success.getName());
        object.setResult(e);
        return object;
    }

    public static <E> R<E> success(E t, String msg) {
        R<E> obj = success(t);
        obj.setMsg(msg);
        return obj;
    }

    public static R fail(BaseEnum codeEnum) {
        R object = new R();
        object.setMsg(codeEnum.getName());
        object.setCode(codeEnum.getCode());
        return object;
    }

    public static R fail(String msg) {
        R object = new R();
        object.setMsg(msg);
        object.setCode(CodeEnum.Fail.getCode());
        return object;
    }

    public static <E> R<E> fail(E e, String msg) {
        R<E> object = new R<>();
        object.setCode(CodeEnum.Fail.getCode());
        object.setMsg(msg);
        object.setResult(e);
        return object;
    }

    public static <E> R<E> res(BaseEnum codeEnum, E e) {
        R<E> object = new R<>();
        object.setMsg(codeEnum.getName());
        object.setCode(codeEnum.getCode());
        object.setResult(e);
        return object;
    }


    public boolean isSuccess() {
        return Objects.equals(CodeEnum.Success.getCode(), this.getCode());
    }

}
