package com.cq.core.boot.commons.exception;

/**
 * @author cqmike
 **/
public class SystemException extends RuntimeException {

  private static final long serialVersionUID = -6049220729887363535L;

  private String msg;

  public SystemException(String msg) {
    super(msg);
    this.msg = msg;
  }
}
