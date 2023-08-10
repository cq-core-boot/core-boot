package com.cq.core.boot.influxdb.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author <a href="mailto:cqmike0315@gmail.com">chenqi</a>
 * @version 1.0
 */
@Data
@Accessors(chain = true)
public class ContinuousQuery {

    private String database;

    private String name;

    private String query;
}
