package com.cq.core.boot.tenant.core;

import cn.hutool.core.util.StrUtil;
import com.cq.core.boot.tenant.config.TenantProperties;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author <a href="mailto:cqmike0315@gmail.com">chenqi</a>
 * @version 1.0
 */
public class DefaultTenantInterceptor extends HandlerInterceptorAdapter {

    private final TenantProperties tenantProperties;

    public static final String DEFAULT_TENANT_KEY = "X-TenantID";

    public DefaultTenantInterceptor(TenantProperties tenantProperties) {
        this.tenantProperties = tenantProperties;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String defaultRequestTenantKey = tenantProperties.getDefaultRequestTenantKey();
        String tenantKey = StrUtil.emptyToDefault(defaultRequestTenantKey, DEFAULT_TENANT_KEY);
        String tenantId = request.getHeader(tenantKey);
        TenantContextHolder.setCurrentTenant(tenantId);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        TenantContextHolder.clean();
    }
}