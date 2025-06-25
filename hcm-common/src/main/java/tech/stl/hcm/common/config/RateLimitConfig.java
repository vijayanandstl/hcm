package tech.stl.hcm.common.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import java.time.Duration;

@Configuration
public class RateLimitConfig {
    
    @Value("${rate.limit.requests:100}")
    private int requestsPerMinute;
    
    @Value("${rate.limit.duration:60}")
    private int durationInSeconds;
    
    @Bean
    public Bucket createNewBucket() {
        Bandwidth limit = Bandwidth.simple(requestsPerMinute, Duration.ofSeconds(durationInSeconds));
        return Bucket4j.builder()
                .addLimit(limit)
                .build();
    }
} 