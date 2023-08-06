package com.cq.core.boot.commons.exception;

import com.cq.core.boot.commons.constants.CodeEnum;
import com.cq.core.boot.commons.model.ValidateResult;
import lombok.Getter;

import java.util.List;

/**
 * @author cqmike
 */
@Getter
public class ValidationException extends BaseException {
    private static final long serialVersionUID = -6488377930742881579L;
    private final List<ValidateResult> result;

    public ValidationException(List<ValidateResult> list) {
        super(CodeEnum.ValidateError);
        this.result = list;
    }
}
