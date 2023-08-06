package com.cq.core.boot.security.exception;

import lombok.Getter;

@Getter
public class ParseTokenException extends BaseAuthenticationException {

  private static final long serialVersionUID = -2968079209755686556L;

  public ParseTokenException(Integer code, String msg) {
    super(msg);
    this.code = code;
    this.msg = msg;
  }

  private final Integer code;

  private final String msg;

}
