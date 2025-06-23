package tech.stl.hcm.organization;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@OpenAPIDefinition(
    info = @Info(
        title = "Organization Service API",
        version = "1.0",
        description = "REST API for the Organization Service"
    )
)
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
@ComponentScan(basePackages = {"tech.stl.hcm.organization", "tech.stl.hcm.common", "tech.stl.hcm.message.broker"})
@EnableJpaRepositories(basePackages = {"tech.stl.hcm.common.db.repositories"})
@EntityScan(basePackages = {"tech.stl.hcm.common.db.entities"})
public class OrganizationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrganizationServiceApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
} 