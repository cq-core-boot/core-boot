package com.cq.core.boot.influxdb.core;


import com.cq.core.boot.influxdb.model.ContinuousQuery;
import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;

import java.util.List;
import java.util.Set;

/**
 * @author <a href="mailto:cqmike0315@gmail.com">chenqi</a>
 * @version 1.0
 */
public interface InfluxdbRepository {

    /**
     * 保存
     *
     * @param model 实体, 需使用相关注解
     * @return
     * @author cqmike
     * @see org.influxdb.annotation.Measurement
     * @see org.influxdb.annotation.Column
     * @see org.influxdb.annotation.TimeColumn
     * @since 1.0.0
     */
    <T> void save(T model);

    /**
     * 查询列表
     *
     * @param clazz 被influxdb注解修饰的类
     * @return
     * @author cqmike
     * @since 1.0.0
     */
    <T> List<T> query(Class<T> clazz);

    /**
     * @param query 参数
     * @param clazz 被influxdb注解修饰的类
     * @return
     * @author cqmike
     * @see org.influxdb.querybuilder.BuiltQuery.QueryBuilder
     * @since 1.0.0
     */
    <T> List<T> query(Query query, Class<T> clazz);

    void write(final String database, final String retentionPolicy, final Point point);

    void write(final Point point);

    void write(final BatchPoints batchPoints);

    void writeWithRetry(final BatchPoints batchPoints);

    /**
     * 创建数据库如何不存在
     *
     * @param name
     * @return
     * @author cqmike
     * @since 1.0.0
     */
    void createDatabaseIfNotExists(final String name);

    /**
     * 删除数据库
     *
     * @param name
     * @return
     * @author cqmike
     * @since 1.0.0
     */
    void deleteDatabase(final String name);

    /**
     * 数据库是否存在
     *
     * @param name
     * @return
     * @author cqmike
     * @since 1.0.0
     */
    boolean existsDatabase(final String name);

    /**
     * 数据库列表
     *
     * @return
     * @author cqmike
     * @since 1.0.0
     */
    Set<String> listDatabases();

    /**
     * 创建rp保留策略
     *
     * @param rpName
     * @param database
     * @param duration          DURATION子句确定InfluxDB保留数据的时间。 <duration>是持续时间字符串或INF（无限）。 保留策略的最短持续时间为1小时，最大持续时间为INF。
     * @param shardDuration     SHARD DURATION子句确定shard group覆盖的时间范围。 <duration>是一个持续时间字符串，不支持INF（无限）持续时间。此设置是可选的。 默认情况下，shard group持续时间由保留策略的DURATION决定：
     * @param replicationFactor 子句确定每个点的多少独立副本存储在集群中，其中n是数据节点的数量。该子句不能用于单节点实例。
     * @param isDefault         将新的保留策略设置为数据库的默认保留策略。此设置是可选的。
     * @return
     * @author cqmike
     * @since 1.0.0
     */
    void createRetentionPolicy(final String rpName, final String database, final String duration,
                               final String shardDuration, final int replicationFactor, final boolean isDefault);

    default void createRetentionPolicy(final String rpName, final String database, final String duration,
                                       final int replicationFactor, final boolean isDefault) {
        createRetentionPolicy(rpName, database, duration, null, replicationFactor, isDefault);
    }

    default void createRetentionPolicy(final String rpName, final String database, final String duration,
                                       final int replicationFactor) {
        createRetentionPolicy(rpName, database, duration, null, replicationFactor, false);
    }

    /**
     * 删除保留策略
     *
     * @param rpName
     * @param database
     * @return
     * @author cqmike
     * @since 1.0.0
     */
    void dropRetentionPolicy(final String rpName, final String database);

    /**
     * 创建连续查询如何不存在
     *
     * @param cqName
     * @param database
     * @param cqQuery
     * @return
     * @author cqmike
     * @since 1.0.0
     */
    void createContinuousQueryIfNotExists(final String cqName, final String database, Query cqQuery);

    /**
     * 连续查询列表
     *
     * @return
     * @author cqmike
     * @since 1.0.0
     */
    List<ContinuousQuery> listContinuousQuery();

    /**
     * 是否存在cq
     *
     * @param cqName
     * @param database
     * @return
     * @author cqmike
     * @since 1.0.0
     */
    boolean existsContinuousQuery(final String cqName, String database);

    /**
     * 删除连续查询
     *
     * @param cqName
     * @param database
     * @return
     * @author cqmike
     * @since 1.0.0
     */
    void dropContinuousQuery(final String cqName, final String database);

    InfluxDB influxDB();
}
