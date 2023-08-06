package com.cq.core.boot.commons.model;

import lombok.Getter;

/**
 * @author cqmike
 */
@Getter
public abstract class AbstractJpaResponse implements Response {

  private static final long serialVersionUID = 1441871034433136190L;
  private Long id;

  private Long createdAt;

  private Long updatedAt;

  private Integer version;


  public void setId(Long id) {
    this.id = id;
  }

  public void setCreatedAt(Long createdAt) {
    this.createdAt = createdAt;
  }

  public void setUpdatedAt(Long updatedAt) {
    this.updatedAt = updatedAt;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }



}
