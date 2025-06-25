# Kafka Wrapper Library

A production-grade Java wrapper library for Apache Kafka that simplifies common Kafka operations. This library provides an easy-to-use interface for producing messages, consuming messages, and performing administrative tasks with Kafka.

## Features

- Simple and intuitive API for Kafka operations
- Type-safe producer and consumer implementations
- Comprehensive admin operations support
- Built-in error handling and logging
- Configurable settings
- Thread-safe implementations

## Requirements

- Java 11 or higher
- Apache Kafka 3.6.1 or higher
- Maven for dependency management

## Installation

Add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>com.kafka.wrapper</groupId>
    <artifactId>kafka-wrapper</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Usage

### Configuration

Create a configuration object for your Kafka clients:

```java
KafkaConfig config = KafkaConfig.defaultBuilder()
    .bootstrapServers("localhost:9092")
    .groupId("my-consumer-group")
    .clientId("my-client")
    .build();
```

### Producer Example

```java
// Create a producer
try (KafkaProducerWrapper<String, String> producer = new KafkaProducerWrapper<>(config)) {
    // Send message asynchronously
    producer.sendAsync("my-topic", "key", "value")
        .thenAccept(metadata -> System.out.println("Message sent to partition " + metadata.partition()))
        .exceptionally(exception -> {
            System.err.println("Failed to send message: " + exception.getMessage());
            return null;
        });

    // Send message synchronously
    RecordMetadata metadata = producer.sendSync("my-topic", "key", "value");
    System.out.println("Message sent to offset " + metadata.offset());
}
```

### Consumer Example

```java
// Create a consumer
try (KafkaConsumerWrapper<String, String> consumer = new KafkaConsumerWrapper<>(config)) {
    // Subscribe to topic
    consumer.subscribe("my-topic");

    // Process messages
    consumer.pollAndProcess(record -> {
        System.out.printf("Received message: key=%s, value=%s%n", 
            record.key(), record.value());
    });
}
```

### Admin Operations Example

```java
// Create admin client
try (KafkaAdminWrapper admin = new KafkaAdminWrapper(config)) {
    // Create a topic
    admin.createTopic("my-topic", 3, (short) 1);

    // List topics
    Set<String> topics = admin.listTopics();
    System.out.println("Available topics: " + topics);

    // Update topic configuration
    Map<String, String> topicConfig = new HashMap<>();
    topicConfig.put("retention.ms", "86400000"); // 24 hours
    admin.updateTopicConfig("my-topic", topicConfig);
}
```

## Error Handling

The library includes comprehensive error handling and logging. All operations that can fail throw appropriate exceptions with detailed error messages. The library uses SLF4J for logging, allowing you to use your preferred logging implementation.

## Best Practices

1. Always use the try-with-resources pattern to ensure proper resource cleanup
2. Configure appropriate timeout values for your use case
3. Implement proper error handling in your application
4. Monitor the logs for any issues
5. Use appropriate serializers/deserializers for your message formats

## Thread Safety

- The `KafkaProducerWrapper` is thread-safe and can be shared across threads
- The `KafkaConsumerWrapper` is NOT thread-safe and should not be shared across threads
- The `KafkaAdminWrapper` is thread-safe for most operations

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License - see the LICENSE file for details. 



@Service("createCandidate")
@RequiredArgsConstructor
public class CreateCandidate extends  {

    private static final Logger logger = LoggerFactory.getLogger(BillingContractConsumer.class);

    private final ObjectMapper mapper;
    private final BillingContractProcessor processor;
    private final ExceptionLoggingService exceptionLoggingService;
    
    @Override
    protected Set<Long> consume(List<ConsumableEvent> events) {
        Set<Long> toBeAcknowledgedOffsets = new HashSet<>();
        String exceptionId = null;

        for (ConsumableEvent event : events) {
            if (null != event) {
                try {
                    byte[] payload = event.getValue().getContent();
                    var payLoadString = new String(payload, StandardCharsets.UTF_8);
                    logger.info("payload: {} for topic:{} and offset:{}", payLoadString,
                            event.getTopic(), event.getOffset());
                    BillingContractDTO request = mapper.readValue(payload, BillingContractDTO.class);

                    var headers = event.getValue().getHeaders();
                    request.addHeadersFromEventToBillingContractDTO(headers);

                    MDC.put(ORDER_ID, request.getId());
                    addLogData(request.getId(), this.getClass().getName(), null);
                    processor.process(request);
                    logger.info("request: {}", request);
                    toBeAcknowledgedOffsets.add(event.getOffset());
                } 
                 catch (Exception e) {
                    exceptionId = exceptionLoggingService.logException(e);
                    MDC.put(EXCEPTION_ID, exceptionId);
                    logger.error(String.format(
                            "Error occurred in consuming BillingContract for Topic %s and offset: %d , %s",
                            event.getTopic(),
                            event.getOffset(),
                            e.getMessage()), e);
                    toBeAcknowledgedOffsets.add(event.getOffset());
                } finally {
                    MDC.clear();
                    ThreadLoggingContextDataHolder.getInstance().removeAllData();
                }
            }
        }
        return toBeAcknowledgedOffsets;
    }

    private void logAndAcknowledgeError(String message, ConsumableEvent event, Set<Long> toBeAcknowledgedOffsets) {
        logger.error("{} and Offset: {}", message, event.getOffset());
        toBeAcknowledgedOffsets.add(event.getOffset());
    }
}

tech.stl.orders.consumers.messagebroker.queue.consumerConfigEntities[16].topicName=billing_contract_eco
tech.stl.orders.consumers.messagebroker.queue.consumerConfigEntities[16].qualifierName=ecbc
tech.stl.orders.consumers.messagebroker.queue.consumerConfigEntities[16].executorBeanName=billingContractConsumer
tech.stl.orders.consumers.messagebroker.queue.consumerConfigEntities[16].consumerEnabled=true
tech.stl.orders.consumers.messagebroker.queue.consumerConfigEntities[16].executorVersion=REPLAY_LIMIT
tech.stl.orders.consumers.messagebroker.queue.consumerConfigEntities[16].deadLetterTopicName=${tech.stl.orders.billing-contract.DLQTopic.deadLetterTopicName}
tech.stl.orders.consumers.messagebroker.queue.consumerConfigEntities[16].replayLimit=1
tech.stl.orders.consumers.messagebroker.queue.consumerConfigEntities[16].visibility-timeout=3600
tech.stl.orders.consumers.messagebroker.queue.consumerConfigEntities[16].countOfMessagesPolled=1