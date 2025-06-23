package tech.stl.hcm.common.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@Configuration
public class MonitoringConfig {
    
    @Value("${spring.application.name:hcm-service}")
    private String applicationName;
    
    @Bean
    public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config().commonTags("application", applicationName);
    }
} 