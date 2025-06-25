package tech.stl.hcm.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import java.util.Optional;
import java.util.UUID;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditConfig {
    
    private static final UUID SYSTEM_USER_ID = UUID.fromString("00000000-0000-0000-0000-000000000000");
    
    @Value("${audit.enabled:true}")
    private boolean auditEnabled;
    
    @Bean
    public AuditorAware<UUID> auditorProvider() {
        return () -> {
            if (!auditEnabled) {
                return Optional.empty();
            }
            return Optional.of(SYSTEM_USER_ID);
        };
    }
} 