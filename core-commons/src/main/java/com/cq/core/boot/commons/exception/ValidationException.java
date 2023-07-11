package com.cq.core.boot.commons.exception;

import com.cq.core.boot.commons.model.ValidateResult;
import lombok.Getter;

import java.util.List;

/**
 * @author cqmike
 */
public class ValidationException extends RuntimeException {
  private static final long serialVersionUID = -6488377930742881579L;
  @Getter
  private List<ValidateResult> result;
  public ValidationException(List<ValidateResult> list){
    super();
    this.result = list;
  }
}
