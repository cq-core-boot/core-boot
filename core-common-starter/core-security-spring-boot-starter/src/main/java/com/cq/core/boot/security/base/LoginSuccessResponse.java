package com.cq.core.boot.security.base;

import lombok.Data;

@Data
public class LoginSuccessResponse {

  private String token;
  private String username;
}
