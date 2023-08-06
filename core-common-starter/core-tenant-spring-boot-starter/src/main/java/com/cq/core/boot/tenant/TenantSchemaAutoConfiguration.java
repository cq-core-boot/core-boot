package com.cq.core.boot.tenant;

import com.cq.core.boot.tenant.config.TenantProperties;
import com.cq.core.boot.tenant.core.CustomTenantSchemaProvider;
import com.cq.core.boot.tenant.core.TenantSchemaIdentifierResolver;
import com.cq.core.boot.tenant.core.TenantSchemaWebConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

/**
 * @author <a href="mailto:cqmike0315@gmail.com">chenqi</a>
 * @version 1.0
 */
@Import({
        TenantSchemaWebConfig.class
})
@EnableConfigurationProperties({TenantProperties.class})
@RequiredArgsConstructor
@ConditionalOnBean(DataSource.class)
public class TenantSchemaAutoConfiguration {

    private final TenantProperties tenantProperties;


    private final DataSource dataSource;

    @Bean
    public CustomTenantSchemaProvider customTenantProvider() {
        return new CustomTenantSchemaProvider(dataSource, tenantProperties);
    }

    @Bean
    public TenantSchemaIdentifierResolver tenantIdentifierResolver() {
        return new TenantSchemaIdentifierResolver(tenantProperties);
    }
}
