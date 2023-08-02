package com.cq.core.boot.tenant.core;

import com.cq.core.boot.tenant.config.TenantProperties;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;

import java.util.Map;

import static org.hibernate.cfg.AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER;

/**
 * @author <a href="mailto:cqmike0315@gmail.com">chenqi</a>
 * @version 1.0
 */
public class TenantSchemaIdentifierResolver implements CurrentTenantIdentifierResolver, HibernatePropertiesCustomizer {

    private final TenantProperties tenantProperties;

    public TenantSchemaIdentifierResolver(TenantProperties tenantProperties) {
        this.tenantProperties = tenantProperties;
    }

    @Override
    public String resolveCurrentTenantIdentifier() {
        String tenantId = TenantContextHolder.getCurrentTenant();
        if (tenantId != null) {
            return tenantId;
        }
        return tenantProperties.getDefaultTenantId();
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put(MULTI_TENANT_IDENTIFIER_RESOLVER, this);
    }
}