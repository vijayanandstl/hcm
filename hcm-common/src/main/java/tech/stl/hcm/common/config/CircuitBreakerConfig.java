package tech.stl.hcm.common.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CircuitBreakerConfig {
    
    @Value("${app.circuit-breaker.sliding-window-size:10}")
    private int slidingWindowSize;
    
    @Value("${app.circuit-breaker.failure-rate-threshold:50}")
    private float failureRateThreshold;
    
    @Value("${app.circuit-breaker.wait-duration-in-open-state:60}")
    private int waitDurationInOpenState;
    
    @Value("${app.circuit-breaker.permitted-number-of-calls-in-half-open-state:5}")
    private int permittedNumberOfCallsInHalfOpenState;
    
    @Value("${app.circuit-breaker.minimum-number-of-calls:10}")
    private int minimumNumberOfCalls;
    
    @Value("${app.circuit-breaker.automatic-transition-from-open-to-half-open-enabled:true}")
    private boolean automaticTransitionFromOpenToHalfOpenEnabled;

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        io.github.resilience4j.circuitbreaker.CircuitBreakerConfig config = io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.custom()
            .slidingWindowType(SlidingWindowType.COUNT_BASED)
            .slidingWindowSize(slidingWindowSize)
            .failureRateThreshold(failureRateThreshold)
            .waitDurationInOpenState(Duration.ofSeconds(waitDurationInOpenState))
            .permittedNumberOfCallsInHalfOpenState(permittedNumberOfCallsInHalfOpenState)
            .minimumNumberOfCalls(minimumNumberOfCalls)
            .automaticTransitionFromOpenToHalfOpenEnabled(automaticTransitionFromOpenToHalfOpenEnabled)
            .build();

        return CircuitBreakerRegistry.of(config);
    }

    @Bean
    public CircuitBreaker defaultCircuitBreaker(CircuitBreakerRegistry registry) {
        return registry.circuitBreaker("default");
    }
} 