package tech.stl.hcm.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import java.util.Collections;

@Configuration
@EnableRetry
public class RetryConfig {
    
    @Value("${retry.max-attempts:3}")
    private int maxAttempts;
    
    @Value("${retry.initial-interval:1000}")
    private long initialInterval;
    
    @Value("${retry.multiplier:2.0}")
    private double multiplier;
    
    @Value("${retry.max-interval:10000}")
    private long maxInterval;
    
    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        
        // Configure retry policy
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy(maxAttempts, 
            Collections.singletonMap(Exception.class, true));
        retryTemplate.setRetryPolicy(retryPolicy);
        
        // Configure backoff policy
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(initialInterval);
        backOffPolicy.setMultiplier(multiplier);
        backOffPolicy.setMaxInterval(maxInterval);
        retryTemplate.setBackOffPolicy(backOffPolicy);
        
        return retryTemplate;
    }
} 