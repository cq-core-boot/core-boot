package com.cq.core.boot.influxdb.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:cqmike0315@gmail.com">chenqi</a>
 * @version 1.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.influxdb")
public class InfluxdbProperties {

    private String host;

    private String user;

    private String password;

    private String defaultDatabase;
}
