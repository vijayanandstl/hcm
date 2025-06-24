package tech.stl.hcm.candidate.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
import java.util.UUID;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class ApplicationConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public AuditorAware<UUID> auditorProvider() {
        // TODO: This should be replaced with a proper implementation that gets the current user from the security context.
        return () -> Optional.of(UUID.fromString("93817383-a36c-4187-b029-75f8c65d2d38"));
    }
} 