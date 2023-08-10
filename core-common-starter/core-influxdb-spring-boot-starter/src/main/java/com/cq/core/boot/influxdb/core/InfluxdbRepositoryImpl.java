package com.cq.core.boot.influxdb.core;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.cq.core.boot.influxdb.model.ContinuousQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBMapper;
import org.influxdb.impl.Preconditions;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:cqmike0315@gmail.com">chenqi</a>
 * @version 1.0
 */
@Slf4j
@RequiredArgsConstructor
public class InfluxdbRepositoryImpl implements InfluxdbRepository {

    private final InfluxDBMapper influxDBMapper;
    private final InfluxDB influxDB;

    @Override
    public <T> void save(T model) {
        influxDBMapper.save(model);
    }

    @Override
    public <T> List<T> query(Class<T> clazz) {
        return influxDBMapper.query(clazz);
    }

    @Override
    public <T> List<T> query(Query query, Class<T> clazz) {
        return influxDBMapper.query(query, clazz);
    }

    @Override
    public void write(String database, String retentionPolicy, Point point) {
        influxDB.write(database, retentionPolicy, point);
    }

    @Override
    public void write(Point point) {
        influxDB.write(point);
    }

    @Override
    public void write(BatchPoints batchPoints) {
        influxDB.write(batchPoints);
    }

    @Override
    public void writeWithRetry(BatchPoints batchPoints) {
        influxDB.writeWithRetry(batchPoints);
    }

    @Override
    public void createDatabaseIfNotExists(String name) {
        Preconditions.checkNonEmptyString(name, "name");
        String createDatabaseQueryString = String.format("CREATE DATABASE \"%s\"", name);
        influxDB.query(new Query(Query.encode(createDatabaseQueryString)));
    }

    @Override
    public void deleteDatabase(String name) {
        Preconditions.checkNonEmptyString(name, "name");
        influxDB.query(new Query(Query.encode("DROP DATABASE \"" + name + "\"")));
    }

    @Override
    public boolean existsDatabase(String name) {
        Preconditions.checkNonEmptyString(name, "name");
        return listDatabases().contains(name);
    }

    @Override
    public Set<String> listDatabases() {
        QueryResult result = influxDB.query(new Query(QueryConstant.SHOW_DATABASE_COMMAND_ENCODED));
        // {"results":[{"series":[{"name":"databases","columns":["name"],"values":[["mydb"]]}]}]}
        // Series [name=databases, columns=[name], values=[[mydb], [unittest_1433605300968]]]
        Optional<Set<String>> optional = Optional.ofNullable(result)
                .map(QueryResult::getResults)
                .map(rs -> rs.get(0))
                .map(QueryResult.Result::getSeries)
                .map(qs -> qs.get(0))
                .map(QueryResult.Series::getValues)
                .map(vs -> vs.stream().flatMap(Collection::stream)
                        .filter(Objects::nonNull)
                        .map(Object::toString)
                        .collect(Collectors.toSet())
                );
        return optional.orElse(Collections.emptySet());
    }

    @Override
    public void createRetentionPolicy(String rpName, String database, String duration, String shardDuration, int replicationFactor, boolean isDefault) {
        Preconditions.checkNonEmptyString(rpName, "retentionPolicyName");
        Preconditions.checkNonEmptyString(database, "database");
        Preconditions.checkNonEmptyString(duration, "retentionDuration");
        Preconditions.checkDuration(duration, "retentionDuration");
        if (shardDuration != null && !shardDuration.isEmpty()) {
            Preconditions.checkDuration(shardDuration, "shardDuration");
        }
        Preconditions.checkPositiveNumber(replicationFactor, "replicationFactor");

        StringBuilder queryBuilder = new StringBuilder("CREATE RETENTION POLICY \"");
        queryBuilder.append(rpName)
                .append("\" ON \"")
                .append(database)
                .append("\" DURATION ")
                .append(duration)
                .append(" REPLICATION ")
                .append(replicationFactor);
        if (shardDuration != null && !shardDuration.isEmpty()) {
            queryBuilder.append(" SHARD DURATION ");
            queryBuilder.append(shardDuration);
        }
        if (isDefault) {
            queryBuilder.append(" DEFAULT");
        }
        influxDB.query(new Query(Query.encode(queryBuilder.toString())));
    }

    @Override
    public void dropRetentionPolicy(String rpName, String database) {
        Preconditions.checkNonEmptyString(rpName, "retentionPolicyName");
        Preconditions.checkNonEmptyString(database, "database");
        StringBuilder queryBuilder = new StringBuilder("DROP RETENTION POLICY \"");
        queryBuilder.append(rpName)
                .append("\" ON \"")
                .append(database)
                .append("\"");
        influxDB.query(new Query(Query.encode(queryBuilder.toString())));
    }

    @Override
    public void createContinuousQueryIfNotExists(String cqName, String database, Query cqQuery) {
        Preconditions.checkNonEmptyString(cqName, "cqName");
        Preconditions.checkNonEmptyString(database, "database");
        com.google.common.base.Preconditions.checkNotNull(cqQuery, "Expecting a non-empty string for cqQuery");
        StringBuilder queryBuilder = new StringBuilder("CREATE CONTINUOUS QUERY \"");
        queryBuilder.append(cqName)
                .append("\" ON \"")
                .append(database)
                .append("\" BEGIN ")
                .append(cqQuery.getCommand())
                .append(" END ");
        influxDB.query(new Query(Query.encode(queryBuilder.toString())));
    }

    @Override
    public List<ContinuousQuery> listContinuousQuery() {
        QueryResult result = influxDB.query(new Query(QueryConstant.SHOW_CONTINUOUS_QUERIES_COMMAND_ENCODED));
        if (Objects.isNull(result)) {
            return Collections.emptyList();
        }
        List<QueryResult.Result> results = result.getResults();
        if (CollUtil.isEmpty(results)) {
            return Collections.emptyList();
        }
        QueryResult.Result resultFirst = results.get(0);
        if (Objects.isNull(resultFirst)) {
            return Collections.emptyList();
        }
        List<QueryResult.Series> series = resultFirst.getSeries();
        if (CollUtil.isEmpty(series)) {
            return Collections.emptyList();
        }
        List<ContinuousQuery> continuousQueries = new ArrayList<>();
        for (QueryResult.Series qs : series) {
            List<List<Object>> values = qs.getValues();
            if (CollUtil.isEmpty(values)) {
                continue;
            }
            ContinuousQuery continuousQuery = null;
            for (List<Object> value : values) {
                if (CollUtil.isEmpty(value) && value.size() < 2) {
                    continue;
                }
                continuousQuery = new ContinuousQuery()
                        .setDatabase(qs.getName())
                        .setName(StrUtil.toStringOrNull(value.get(0)))
                        .setQuery(StrUtil.toStringOrNull(value.get(1)));
                continuousQueries.add(continuousQuery);
            }
        }
        return continuousQueries;

    }

    @Override
    public boolean existsContinuousQuery(String cqName, String database) {
        Preconditions.checkNonEmptyString(cqName, "cqName");
        Preconditions.checkNonEmptyString(database, "database");
        List<ContinuousQuery> continuousQueries = listContinuousQuery();
        if (CollUtil.isEmpty(continuousQueries)) {
            return false;
        }
        return continuousQueries.stream().anyMatch(cq -> StrUtil.equals(cqName, cq.getName()) && StrUtil.equals(cq.getDatabase(), database));
    }

    @Override
    public void dropContinuousQuery(String cqName, String database) {
        Preconditions.checkNonEmptyString(cqName, "cqName");
        Preconditions.checkNonEmptyString(database, "database");
        StringBuilder queryBuilder = new StringBuilder("DROP CONTINUOUS QUERY \"");
        queryBuilder.append(cqName)
                .append("\" ON \"")
                .append(database)
                .append("\"");
        influxDB.query(new Query(Query.encode(queryBuilder.toString())));
    }

    @Override
    public InfluxDB influxDB() {
        return influxDB;
    }
}
