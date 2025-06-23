package tech.stl.hcm.message.broker.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
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

    private static final String BOOTSTRAP_SERVERS = "10.19.5.50:30000";

    public <T> void startListening(String topic, String groupId, Class<T> valueType, MessageHandler<T> handler) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, CustomDeserializer.class.getName());
        props.put("generic.deserializer.target.class", valueType.getName());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false); // manual commit
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        ConsumerFactory<String, T> factory = new DefaultKafkaConsumerFactory<>(props);

        ContainerProperties containerProps = new ContainerProperties(topic);
        containerProps.setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);

        containerProps.setMessageListener((AcknowledgingMessageListener<String, T>) (record, acknowledgment) -> {
            try {
                //handler.handle(record.value());
                if (handler instanceof MessageHandler) {
                    ((MessageHandler<T>) handler).handle(record.value(), record.key());
                }

                String key = record.key();
                log.info("Consumed and handled message: topic={}, key={}, partition={}, offset={}",
                        record.topic(), key, record.partition(), record.offset());
                acknowledgment.acknowledge(); // manual commit
                log.info("Manually committed offset={}", record.offset());
            } catch (Exception ex) {
                log.error("Error handling record from topic {}: {}", topic, ex.getMessage(), ex);
            }
        });

        KafkaMessageListenerContainer<String, T> container = new KafkaMessageListenerContainer<>(factory, containerProps);
        container.start();
        log.info("Started listener container for topic: {}", topic);
    }
}
