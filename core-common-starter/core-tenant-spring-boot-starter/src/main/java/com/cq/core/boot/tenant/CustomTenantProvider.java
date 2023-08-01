package com.cq.core.boot.tenant;

import org.hibernate.HibernateException;
import org.hibernate.MultiTenancyStrategy;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import static org.hibernate.cfg.AvailableSettings.MULTI_TENANT;
import static org.hibernate.cfg.AvailableSettings.MULTI_TENANT_CONNECTION_PROVIDER;

/**
 * @author <a href="mailto:cqmike0315@gmail.com">chenqi</a>
 * @version 1.0
 */
@Component
public class CustomTenantProvider implements MultiTenantConnectionProvider, HibernatePropertiesCustomizer {

    private static final long serialVersionUID = -5437868050157073731L;
    private final DataSource dataSource;

    public CustomTenantProvider(@Qualifier("dataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Connection getAnyConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connection.close();
    }

    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
        tenantIdentifier = TenantContext.getCurrentTenant();
        final Connection connection = getAnyConnection();
        try {
            if (tenantIdentifier != null) {
                connection.createStatement().execute("USE " + tenantIdentifier);
            } else {
                connection.createStatement().execute("USE " + TenantIdentifierResolver.DEFAULT_TENANT_ID);
            }
        } catch (SQLException e) {
            throw new HibernateException(
                    "Problem setting schema to " + tenantIdentifier,
                    e
            );
        }
        return connection;
    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        // 切换数据库
        String sql = String.format("USE %s", TenantIdentifierResolver.DEFAULT_TENANT_ID);
        connection.createStatement().execute(sql);
        connection.close();
    }

    @Override
    public boolean isUnwrappableAs(Class unwrapType) {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> unwrapType) {
        return null;
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return true;
    }

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put(MULTI_TENANT_CONNECTION_PROVIDER, this);
        hibernateProperties.put(MULTI_TENANT, MultiTenancyStrategy.SCHEMA);
    }
}