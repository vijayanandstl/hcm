package tech.stl.hcm.message.broker.controller;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.stl.hcm.message.broker.admin.AdminService;

@Slf4j
@RestController
@RequestMapping("/admin/kafka")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService kafkaAdminService;

    // http://localhost:8989/admin/kafka/topics
    @PostMapping("/topics")
    public ResponseEntity<String> createTopic(@RequestParam String name,
                                              @RequestParam(defaultValue = "3") int partitions,
                                              @RequestParam(defaultValue = "1") short replication) {
        kafkaAdminService.createTopic(name, partitions, replication);
        return ResponseEntity.ok("Topic created: " + name);
    }

    @DeleteMapping("/topics/{name}")
    public ResponseEntity<String> deleteTopic(@PathVariable String name) {
        kafkaAdminService.deleteTopic(name);
        return ResponseEntity.ok("Topic deleted: " + name);
    }

    @GetMapping("/topics")
    public ResponseEntity<Set<String>> listTopics() throws Exception {
        return ResponseEntity.ok(kafkaAdminService.listTopics());
    }

    @GetMapping("/topics/{name}/offsets")
    public ResponseEntity<Map<TopicPartition, Long>> getEndOffsets(@PathVariable String name) throws Exception {
        return ResponseEntity.ok(kafkaAdminService.getEndOffsets(name));
    }

    /**
     * Get offsets for a given consumer group.
     */
    @GetMapping("/consumer-groups/{groupId}/offsets")
    public Map<TopicPartition, OffsetAndMetadata> getConsumerGroupOffsets(@PathVariable String groupId) throws Exception {
        log.info("Fetching offsets for consumer group: {}", groupId);
        return kafkaAdminService.getConsumerGroupOffsets(groupId);
    }

    /**
     * Describe a topic (partitions, leader, replicas etc).
     */
    @GetMapping("/topics/{topic}/describe")
    public TopicDescription describeTopic(@PathVariable String topic) throws ExecutionException, InterruptedException {
        log.info("Describing topic: {}", topic);
        return kafkaAdminService.describeTopic(topic);
    }
}
