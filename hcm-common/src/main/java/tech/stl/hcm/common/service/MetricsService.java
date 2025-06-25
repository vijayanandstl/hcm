package tech.stl.hcm.common.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class MetricsService {
    protected final MeterRegistry registry;

    public MetricsService(MeterRegistry registry) {
        this.registry = registry;
    }

    protected Counter createCounter(String name, String description) {
        return Counter.builder(name)
                .description(description)
                .register(registry);
    }

    protected void incrementCounter(Counter counter) {
        counter.increment();
    }
} 