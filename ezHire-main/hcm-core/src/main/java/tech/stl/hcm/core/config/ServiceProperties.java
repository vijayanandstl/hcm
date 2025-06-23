package tech.stl.hcm.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "services")
@Data
public class ServiceProperties {
    private String candidateUrl;
    private String tenantUrl;
    private String organizationUrl;
} 