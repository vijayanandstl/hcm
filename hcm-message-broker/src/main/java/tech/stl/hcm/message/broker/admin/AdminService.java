package tech.stl.hcm.message.broker.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ConsumerGroupListing;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminService {

    private final AdminClient adminClient;

    public void createTopic(String name, int partitions, short replicationFactor) {
        NewTopic newTopic = new NewTopic(name, partitions, replicationFactor);
        adminClient.createTopics(List.of(newTopic));
    }

    public void deleteTopic(String name) {
        adminClient.deleteTopics(List.of(name));
    }

    public Set<String> listTopics() throws Exception {
        return adminClient.listTopics().names().get();
    }

    public Map<TopicPartition, Long> getEndOffsets(String topic) throws Exception {
        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(
                Map.of(
                        ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.19.5.50:30000",
                        ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName(),
                        ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName(),
                        ConsumerConfig.GROUP_ID_CONFIG, "offset-checker"
                )
        )) {
            List<PartitionInfo> partitions = consumer.partitionsFor(topic);

            List<TopicPartition> topicPartitions = partitions.stream()
                    .map(p -> new TopicPartition(topic, p.partition()))
                    .collect(Collectors.toList());

            consumer.assign(topicPartitions);
            consumer.seekToEnd(topicPartitions);

            Map<TopicPartition, Long> endOffsets = new HashMap<>();
            for (TopicPartition tp : topicPartitions) {
                endOffsets.put(tp, consumer.position(tp));
            }

            return endOffsets;
        }
    }

    public List<String> listConsumerGroups() throws Exception {
        return adminClient.listConsumerGroups().all().get()
                .stream()
                .map(ConsumerGroupListing::groupId)
                .collect(Collectors.toList());
    }

    public Map<TopicPartition, OffsetAndMetadata> getConsumerGroupOffsets(String groupId) throws Exception {
        return adminClient.listConsumerGroupOffsets(groupId).partitionsToOffsetAndMetadata().get();
    }

    public TopicDescription describeTopic(String topic) throws ExecutionException, InterruptedException {
        return adminClient.describeTopics(List.of(topic)).all().get().get(topic);
    }
}
