package com.cq.core.boot.influxdb.core;

import cn.hutool.core.lang.Assert;
import com.cq.core.boot.influxdb.utils.FieldUtil;
import org.influxdb.annotation.Measurement;
import org.influxdb.querybuilder.BuiltQuery;
import org.influxdb.querybuilder.SelectionQueryImpl;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:cqmike0315@gmail.com">chenqi</a>
 * @version 1.0
 */
public final class QueryHelper {


    @SafeVarargs
    public static <T, R> SelectionQueryImpl select(SFunction<T, R>... functions) {
        Assert.noNullElements(functions, "functions not null");
        return BuiltQuery.QueryBuilder.select(Arrays.stream(functions).map(FieldUtil::getColumnName).toArray());
    }

    public static <T, R> String $(SFunction<T, R> function) {
        return FieldUtil.getColumnName(function);
    }

    public static String db(Class<?> clazz) {
        return clazz.getAnnotation(Measurement.class).database();
    }

    public static String m(final Class<?> clazz) {
        return clazz.getAnnotation(Measurement.class).name();
    }

    public static String rp(final Class<?> clazz) {
        return clazz.getAnnotation(Measurement.class).retentionPolicy();
    }

    public static TimeUnit tu(final Class<?> clazz) {
        return clazz.getAnnotation(Measurement.class).timeUnit();
    }


}
