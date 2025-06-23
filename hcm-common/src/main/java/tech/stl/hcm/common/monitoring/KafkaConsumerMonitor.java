package tech.stl.hcm.common.monitoring;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListConsumerGroupOffsetsResult;
import org.apache.kafka.clients.admin.ListOffsetsResult;
import org.apache.kafka.clients.admin.OffsetSpec;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

@Component
public class KafkaConsumerMonitor {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerMonitor.class);
    
    private final KafkaAdmin kafkaAdmin;
    private final MeterRegistry meterRegistry;
    
    public KafkaConsumerMonitor(KafkaAdmin kafkaAdmin, MeterRegistry meterRegistry) {
        this.kafkaAdmin = kafkaAdmin;
        this.meterRegistry = meterRegistry;
    }
    
    @Scheduled(fixedRate = 60000) // Run every minute
    public void monitorConsumerGroups() {
        try {
            AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties());
            ListConsumerGroupOffsetsResult result = adminClient.listConsumerGroupOffsets("hcm-candidate-service-group");
            Map<TopicPartition, OffsetAndMetadata> offsets = result.partitionsToOffsetAndMetadata().get();
            
            // Record consumer lag
            for (Map.Entry<TopicPartition, OffsetAndMetadata> entry : offsets.entrySet()) {
                TopicPartition partition = entry.getKey();
                OffsetAndMetadata offset = entry.getValue();
                
                // Get the end offset for the partition
                Map<TopicPartition, OffsetSpec> topicPartitionOffsets = 
                    Collections.singletonMap(partition, OffsetSpec.latest());
                Map<TopicPartition, ListOffsetsResult.ListOffsetsResultInfo> endOffsets =
                    adminClient.listOffsets(topicPartitionOffsets).all().get();
                
                long consumerOffset = offset.offset();
                long endOffset = endOffsets.get(partition).offset();
                long lag = endOffset - consumerOffset;
                
                // Record the lag metric
                meterRegistry.gauge("kafka.consumer.lag", 
                    Tags.of("topic", partition.topic(), "partition", String.valueOf(partition.partition())), 
                    lag);
            }
        } catch (Exception e) {
            logger.error("Error monitoring consumer groups", e);
        }
    }
} 