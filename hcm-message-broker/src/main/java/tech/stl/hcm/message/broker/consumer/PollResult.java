package tech.stl.hcm.message.broker.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class PollResult<T> {
    private final KafkaConsumer<String, T> consumer;
    private final ConsumerRecords<String, T> records;

    public PollResult(KafkaConsumer<String, T> consumer, ConsumerRecords<String, T> records) {
        this.consumer = consumer;
        this.records = records;
    }

    public KafkaConsumer<String, T> getConsumer() {
        return consumer;
    }

    public ConsumerRecords<String, T> getRecords() {
        return records;
    }
}
