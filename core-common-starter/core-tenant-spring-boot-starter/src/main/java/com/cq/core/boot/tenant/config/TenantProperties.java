package com.cq.core.boot.tenant.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author <a href="mailto:cqmike0315@gmail.com">chenqi</a>
 * @version 1.0
 */
@Data
@ConfigurationProperties(prefix = "tenant.schema")
public class TenantProperties {

    private String defaultTenantId;
    private String defaultRequestTenantKey = "X-TenantID";
}
