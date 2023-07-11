package com.cq.core.boot.commons.model;

import lombok.Data;

import java.util.Map;

/**
 * @author cqmike
 */
@Data
public class PageRequestWrapper<T> {

  private T bean;
  private Integer pageSize;
  private Integer page;
  private Map<String, String> sorts;
}
