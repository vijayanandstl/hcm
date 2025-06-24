package tech.stl.hcm.candidate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
@ComponentScan(basePackages = {"tech.stl.hcm.candidate", "tech.stl.hcm.consumers", "tech.stl.hcm.message.broker"})
@EnableJpaRepositories("tech.stl.hcm.common.db.repositories")
@EntityScan("tech.stl.hcm.common.db.entities")
public class CandidateServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CandidateServiceApplication.class, args);
    }

} 