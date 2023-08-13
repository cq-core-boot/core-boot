package com.cq.core.boot.influxdb.core;

import java.io.Serializable;
import java.util.function.Function;

/**
 * SFunction
 *
 * @author cqmike
 **/
@FunctionalInterface
public interface SFunction<T, R> extends Function<T, R>, Serializable {
}