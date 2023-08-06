package com.cq.core.boot.tenant.core;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * @author <a href="mailto:cqmike0315@gmail.com">chenqi</a>
 * @version 1.0
 */
public class TenantContextHolder {
    private static final TransmittableThreadLocal<String> currentTenant = new TransmittableThreadLocal<>();

    public static void setCurrentTenant(String tenant) {
        currentTenant.set(tenant);
    }

    public static String getCurrentTenant() {
        return currentTenant.get();
    }

    public static void clean() {
        currentTenant.remove();
    }
}
