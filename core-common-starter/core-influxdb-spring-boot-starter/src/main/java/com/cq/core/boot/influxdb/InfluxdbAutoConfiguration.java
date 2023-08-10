package com.cq.core.boot.influxdb;

import com.cq.core.boot.influxdb.config.InfluxdbProperties;
import com.cq.core.boot.influxdb.core.InfluxdbRepository;
import com.cq.core.boot.influxdb.core.InfluxdbRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.impl.InfluxDBMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:cqmike0315@gmail.com">chenqi</a>
 * @version 1.0
 */
@RequiredArgsConstructor
@EnableConfigurationProperties(InfluxdbProperties.class)
public class InfluxdbAutoConfiguration {

    private final InfluxdbProperties influxdbProperties;

    @Bean
    @ConditionalOnMissingBean
    public InfluxDB influxdb() {
        InfluxDB influxDB = InfluxDBFactory.connect(influxdbProperties.getHost(), influxdbProperties.getUser(), influxdbProperties.getPassword());
        influxDB.setDatabase(influxdbProperties.getDefaultDatabase());
        influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
        influxDB.enableBatch(2000, 100, TimeUnit.MILLISECONDS);
        return influxDB;
    }

    @Bean
    @ConditionalOnMissingBean
    public InfluxDBMapper influxDBMapper() {
        return new InfluxDBMapper(influxdb());
    }


    @Bean
    @ConditionalOnBean({InfluxDB.class, InfluxDBMapper.class})
    public InfluxdbRepository influxdbRepository() {
        return new InfluxdbRepositoryImpl(influxDBMapper(), influxdb());
    }
}
