package tech.stl.hcm.message.broker.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.stereotype.Service;
import tech.stl.hcm.message.broker.deserializer.CustomDeserializer;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ListenerService {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    private final ObjectMapper objectMapper;

    public <T> void startListening(String topic, String groupId, Class<T> valueType, MessageHandler<T> handler) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put("generic.deserializer.target.class", valueType.getName());

        CustomDeserializer<T> valueDeserializer = new CustomDeserializer<>(objectMapper);

        ConsumerFactory<String, T> factory = new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), valueDeserializer);

        ContainerProperties containerProps = new ContainerProperties(topic);
        containerProps.setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);

        containerProps.setMessageListener((AcknowledgingMessageListener<String, T>) (record, acknowledgment) -> {
            log.debug("Received message from topic {}: key={}, value={}", record.topic(), record.key(), record.value());
            try {
                handler.handle(record.value(), record.key());

                if (acknowledgment != null) {
                    acknowledgment.acknowledge();
                    log.info("Successfully processed and acknowledged message from topic {}: key={}, partition={}, offset={}",
                            record.topic(), record.key(), record.partition(), record.offset());
                }
            } catch (Exception ex) {
                log.error("Error handling record from topic {}: key={}, value={}. Error: {}",
                        record.topic(), record.key(), record.value(), ex.getMessage(), ex);
            }
        });

        KafkaMessageListenerContainer<String, T> container = new KafkaMessageListenerContainer<>(factory, containerProps);
        container.start();
        log.info("Started listener container for topic: {}", topic);
    }
}
