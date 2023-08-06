package com.cq.core.boot.tenant.core;

import com.cq.core.boot.tenant.config.TenantProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author <a href="mailto:cqmike0315@gmail.com">chenqi</a>
 * @version 1.0
 */
@Configuration
public class TenantSchemaWebConfig implements WebMvcConfigurer {

    private final TenantProperties tenantProperties;

    public TenantSchemaWebConfig(TenantProperties tenantProperties) {
        this.tenantProperties = tenantProperties;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tenantInterceptor());
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    @Bean("tenantInterceptor")
    @ConditionalOnMissingBean(name = "tenantInterceptor")
    public DefaultTenantInterceptor tenantInterceptor() {
        return new DefaultTenantInterceptor(tenantProperties);
    }


}