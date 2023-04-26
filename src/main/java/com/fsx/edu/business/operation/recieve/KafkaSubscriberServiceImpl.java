package com.fsx.edu.business.operation.recieve;


import com.fsx.edu.business.codes.KafkaTopicName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@Slf4j
@Component
public class KafkaSubscriberServiceImpl implements KafkaSubscriberService {

    @KafkaListener(topics = KafkaTopicName.FSX_TOPIC, groupId = "j2os-p3-r3-app")
    public void receive(String message) {
        log.info(String.format("message received->%s", message));
        
    }

}