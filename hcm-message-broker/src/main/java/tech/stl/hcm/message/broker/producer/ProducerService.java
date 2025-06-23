package tech.stl.hcm.message.broker.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProducerService {

    private final ProducerFactory producerFactory;
    private static final String BOOTSTRAP_SERVERS = "10.19.5.50:30000";

    public <T> void publishMessage(String topic, String key, T payload) {
        try (Producer<String, T> producer = producerFactory.createProducer(BOOTSTRAP_SERVERS)) {
            ProducerRecord<String, T> record = new ProducerRecord<>(topic, key, payload);
            Future<RecordMetadata> future = producer.send(record, (metadata, exception) -> {
                if (exception == null) {
                    log.info("Produced message to topic={}, partition={}, offset={}, key={}",
                            metadata.topic(), metadata.partition(), metadata.offset(), key);
                } else {
                    log.error("Failed to produce message to Kafka", exception);
                }
            });

            producer.flush();
        } catch (Exception e) {
            log.error("Failed to produce message to Kafka", e);
        }
    }
}