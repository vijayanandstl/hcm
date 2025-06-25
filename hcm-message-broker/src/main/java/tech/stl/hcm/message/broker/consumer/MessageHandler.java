package tech.stl.hcm.message.broker.consumer;


public interface MessageHandler<T> {
    default void handle(T message) {
        // fallback if key is not used
    }

    default void handle(T message, String key) {
        handle(message); // or leave empty
    }
}

