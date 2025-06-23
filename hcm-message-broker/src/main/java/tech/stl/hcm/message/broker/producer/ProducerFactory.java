package tech.stl.hcm.message.broker.producer;


import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Service;
import tech.stl.hcm.message.broker.serializer.CustomSerializer;

@Service
public class ProducerFactory {

    public <T> Producer<String, T> createProducer(String bootstrapServers) {
        Properties properties = new Properties();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "generic-kafka-producer");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CustomSerializer.class.getName());

        return new KafkaProducer<>(properties);
    }
}
