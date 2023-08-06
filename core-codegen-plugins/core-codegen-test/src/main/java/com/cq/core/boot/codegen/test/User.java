package com.cq.core.boot.codegen.test;

import com.cq.core.boot.codegen.processor.api.GenFeign;
import com.cq.core.boot.codegen.processor.api.GenQueryRequest;
import com.cq.core.boot.codegen.processor.api.GenResponse;
import com.cq.core.boot.codegen.processor.api.GenUpdateRequest;
import com.cq.core.boot.codegen.processor.controller.GenController;
import com.cq.core.boot.codegen.processor.creator.GenCreator;
import com.cq.core.boot.codegen.processor.query.GenQuery;
import com.cq.core.boot.codegen.processor.repository.GenRepository;
import com.cq.core.boot.codegen.processor.service.GenService;
import com.cq.core.boot.codegen.processor.updater.GenUpdater;
import com.cq.core.boot.codegen.processor.vo.GenVo;
import com.cq.core.boot.jpa.support.BaseJpaAggregate;

/**
 * @author gim 2022/1/11 10:53 下午
 */
//
@GenVo(pkgName = "com.cq.core.boot.codegen.test.vo")
@GenCreator(pkgName = "com.cq.core.boot.codegen.test.creator")
@GenUpdater(pkgName = "com.cq.core.boot.codegen.test.updater")
@GenRepository(pkgName = "com.cq.core.boot.codegen.test.repository")
@GenService(pkgName = "com.cq.core.boot.codegen.test.service")
@GenController(pkgName = "com.cq.core.boot.codegen.test.controller")
@GenQuery(pkgName = "com.cq.core.boot.codegen.test.query")
@GenUpdateRequest(pkgName = "com.cq.core.boot.codegen.test.api.request")
@GenQueryRequest(pkgName = "com.cq.core.boot.codegen.test.api.request")
@GenResponse(pkgName = "com.cq.core.boot.codegen.test.api.response")
@GenFeign(pkgName = "com.cq.core.boot.codegen.test.api.service")
public class User extends BaseJpaAggregate {

  private String username;

  private String password;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void valid() {
  }

  public void invalid() {
  }
}
