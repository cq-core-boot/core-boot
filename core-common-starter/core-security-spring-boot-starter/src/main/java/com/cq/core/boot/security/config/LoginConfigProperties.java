package com.cq.core.boot.security.config;


import com.cq.core.boot.commons.annotation.FieldDesc;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "core.security")
public class LoginConfigProperties {

  @FieldDesc(name = "token过期时间默认30分钟")
  private Long expired = 30L;

  @FieldDesc(name = "是否开启权限")
  private boolean enable;

}
