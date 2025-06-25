package tech.stl.hcm.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
@ComponentScan(basePackages = {"tech.stl.hcm.core", "tech.stl.hcm.common", "tech.stl.hcm.message.broker"})
@EnableJpaRepositories(basePackages = {"tech.stl.hcm.common.db.repositories"})
@EntityScan(basePackages = {"tech.stl.hcm.common.db.entities"})
public class HcmCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(HcmCoreApplication.class, args);
    }

} 