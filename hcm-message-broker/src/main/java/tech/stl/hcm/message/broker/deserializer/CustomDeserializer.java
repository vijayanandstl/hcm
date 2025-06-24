package tech.stl.hcm.message.broker.deserializer;


import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import org.apache.kafka.common.serialization.Deserializer;

public class CustomDeserializer<T> implements Deserializer<T> {

    private final ObjectMapper objectMapper;
    private Class<T> type;
    
    public CustomDeserializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void configure(Map<String, ?> configs, boolean isKey) {
        String className = (String) configs.get("generic.deserializer.target.class");
        try {
            this.type = (Class<T>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Target class not found for deserialization", e);
        }
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, type);
        } catch (IOException e) {
            throw new RuntimeException("Deserialization failed", e);
        }
    }

    @Override
    public void close() {
    }
}
