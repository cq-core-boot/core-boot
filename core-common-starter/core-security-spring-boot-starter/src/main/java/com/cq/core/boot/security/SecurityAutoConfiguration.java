package com.cq.core.boot.security;

import com.cq.core.boot.security.config.SecurityConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author gim
 */
@Configuration
@ConditionalOnProperty(prefix = "op.security", name = "enable", havingValue = "true")
public class SecurityAutoConfiguration {

    @Configuration
    @ComponentScan(value = {"com.cq.core.boot.security.base", "com.cq.core.boot.security.config"})
    @Import(value = {SecurityConfig.class})
    public static class AdminSecurityConfig {

    }
}
