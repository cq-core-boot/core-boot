package com.cq.core.boot.security.base.extension;


import com.cq.core.boot.security.base.jwt.BaseJwtUser;

/**
 * @author gim
 */
public interface UserContextAware {

  /**
   * 处理token
   *
   * @param token
   * @return
   */
  BaseJwtUser processToken(String token);

}
