package com.cq.core.boot.commons.exception;

import com.only4play.common.model.ValidateResult;
import lombok.Getter;

import java.util.List;

/**
 * @author cqmike
 */
public class ValidationException extends RuntimeException {
  @Getter
  private List<ValidateResult> result;
  public ValidationException(List<ValidateResult> list){
    super();
    this.result = list;
  }
}
