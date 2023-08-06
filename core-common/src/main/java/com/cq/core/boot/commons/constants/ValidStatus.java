package com.cq.core.boot.commons.constants;

import java.util.Optional;

/**
 * @author cqmike
 **/
public enum ValidStatus implements BaseEnum<ValidStatus> {
  /**
   * 有效
   */
  VALID(1, "valid"),
  /**
   * 无效
   */
  INVALID(0, "invalid");
    private final Integer code;
    private final String name;

  ValidStatus(Integer code, String msg) {
    this.code = code;
    this.name = msg;
  }

  @Override
  public Integer getCode() {
    return code;
  }

  @Override
  public String getName() {
    return name;
  }

  public static Optional<ValidStatus> of(Integer code) {
    return Optional.ofNullable(BaseEnum.parseByCode(ValidStatus.class, code));
  }
}