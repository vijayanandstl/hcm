package tech.stl.hcm.message.broker.admin;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaAdminConfig {

    @Bean
    public AdminClient kafkaAdminClient() {
        Map<String, Object> config = new HashMap<>();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "10.19.5.50:30000");

        return AdminClient.create(config);
    }
}
