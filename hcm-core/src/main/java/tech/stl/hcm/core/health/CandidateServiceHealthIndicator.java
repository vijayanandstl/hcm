package tech.stl.hcm.core.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import tech.stl.hcm.core.config.ServiceProperties;

@Component
public class CandidateServiceHealthIndicator implements HealthIndicator {

    private final WebClient webClient;
    private final ServiceProperties serviceProperties;

    public CandidateServiceHealthIndicator(WebClient.Builder webClientBuilder, ServiceProperties serviceProperties) {
        this.webClient = webClientBuilder.build();
        this.serviceProperties = serviceProperties;
    }

    @Override
    public Health health() {
        try {
            webClient.get()
                    .uri(serviceProperties.getCandidateUrl() + "/actuator/health")
                    .retrieve()
                    .toBodilessEntity()
                    .block();
            return Health.up().withDetail("service", "Candidate Service is up").build();
        } catch (Exception ex) {
            return Health.down().withDetail("service", "Candidate Service is down").withException(ex).build();
        }
    }
} 