package tech.stl.hcm.core.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import tech.stl.hcm.core.config.ServiceProperties;

@Component
public class TenantServiceHealthIndicator implements HealthIndicator {

    private final WebClient webClient;
    private final ServiceProperties serviceProperties;

    public TenantServiceHealthIndicator(WebClient.Builder webClientBuilder, ServiceProperties serviceProperties) {
        this.webClient = webClientBuilder.build();
        this.serviceProperties = serviceProperties;
    }

    @Override
    public Health health() {
        try {
            webClient.get()
                    .uri(serviceProperties.getTenantUrl() + "/actuator/health")
                    .retrieve()
                    .toBodilessEntity()
                    .block();
            return Health.up().withDetail("service", "Tenant Service is up").build();
        } catch (Exception ex) {
            return Health.down().withDetail("service", "Tenant Service is down").withException(ex).build();
        }
    }
} 