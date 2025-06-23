package tech.stl.hcm.consumers;

public interface KafkaMessageHandler<T> {
    void handle(T message);
}
