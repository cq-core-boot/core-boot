package com.cq.core.boot.commons.mapper;


import com.cq.core.boot.commons.constants.ValidStatus;

public class GenericEnumMapper {

  public Integer asInteger(ValidStatus status) {
    return status.getCode();
  }

  public ValidStatus asValidStatus(Integer code) {
    return ValidStatus.of(code).orElse(ValidStatus.INVALID);
  }
}