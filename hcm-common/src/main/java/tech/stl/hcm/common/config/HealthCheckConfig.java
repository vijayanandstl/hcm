package tech.stl.hcm.common.config;

import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Health.Builder;

@Configuration
public class HealthCheckConfig {
    
    @Bean
    public HealthIndicator serviceHealthIndicator() {
        return () -> {
            try {
                // Add service-specific health check logic here
                return Health.up().build();
            } catch (Exception e) {
                return Health.down().withException(e).build();
            }
        };
    }
} 