package com.cq.core.boot.security.exception;


import lombok.Getter;

@Getter
public class CustomAuthenticationException extends BaseAuthenticationException {

  private static final long serialVersionUID = -1945190802123880570L;
  private final Integer code;

  public CustomAuthenticationException(Integer code, String msg) {
    super(msg);
    this.code = code;
  }

}

