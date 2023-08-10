package com.cq.core.boot.influxdb.core;

import org.influxdb.dto.Query;

/**
 * @author <a href="mailto:cqmike0315@gmail.com">chenqi</a>
 * @version 1.0
 */
final class QueryConstant {

    public static final String SHOW_DATABASE_COMMAND_ENCODED = Query.encode("SHOW DATABASES");
    public static final String SHOW_CONTINUOUS_QUERIES_COMMAND_ENCODED = Query.encode("SHOW CONTINUOUS QUERIES");
}
