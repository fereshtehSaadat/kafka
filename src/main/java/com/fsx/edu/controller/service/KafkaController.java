package com.fsx.edu.controller.service;

import com.fsx.edu.TopicViewModel;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class KafkaController {

    private final KafkaAdmin kafkaAdmin;
    private final KafkaTemplate<String, String> kafkaTemplate;

//    @PostMapping("/topics")
//    public ResponseEntity<String> createTopic(@RequestParam String topicName) {
//        NewTopic newTopic = new NewTopic(topicName, 1, (short) 1);
//        kafkaAdmin.createOrModifyTopics(newTopic);
//        kafkaTemplate.send(topicName, "Test message");
//        return ResponseEntity.ok("Topic created successfully");
//    }

    @PostMapping("/topicsWithoutSend")
    public void topicsWithoutSend(@RequestParam String topicName) {
        NewTopic newTopic = new NewTopic(topicName, 1, (short) 1);
        kafkaAdmin.createOrModifyTopics(newTopic);
    }
    @PostMapping("/topics")
    public ResponseEntity<String> createTopic(@RequestBody TopicViewModel topic) {
        NewTopic newTopic = new NewTopic(topic.name(), topic.partition(),topic.replicas ());
        kafkaAdmin.createOrModifyTopics(newTopic);
        kafkaTemplate.send(topic.name(), "Test message");
        return ResponseEntity.ok("Topic created successfully");
    }
}
