package com.cq.core.boot.security.base.jwt;

import com.cq.core.boot.commons.annotation.FieldDesc;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * 基础session 数据 通过token 直接能换取的信息
 */
@Data
public abstract class BaseJwtUser implements Serializable {

  private static final long serialVersionUID = -3472217110213049195L;
  @FieldDesc(name = "用户Id")
  private Long userId;

  @FieldDesc(name = "用户名")
  private String username;

  @FieldDesc(name = "额外信息")
  private Map<String, String> extInfo;

  @FieldDesc(name = "权限信息")
  private Collection<? extends GrantedAuthority> authorities;
}
