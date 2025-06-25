package tech.stl.hcm.core.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import tech.stl.hcm.core.config.ServiceProperties;

@Component
public class OrganizationServiceHealthIndicator implements HealthIndicator {

    private final WebClient webClient;
    private final ServiceProperties serviceProperties;

    public OrganizationServiceHealthIndicator(WebClient.Builder webClientBuilder, ServiceProperties serviceProperties) {
        this.webClient = webClientBuilder.build();
        this.serviceProperties = serviceProperties;
    }

    @Override
    public Health health() {
        try {
            webClient.get()
                    .uri(serviceProperties.getOrganizationUrl() + "/actuator/health")
                    .retrieve()
                    .toBodilessEntity()
                    .block();
            return Health.up().withDetail("service", "Organization Service is up").build();
        } catch (Exception ex) {
            return Health.down().withDetail("service", "Organization Service is down").withException(ex).build();
        }
    }
} 