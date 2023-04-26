package com.fsx.edu;


import com.fsx.edu.business.codes.KafkaTopicName;
import com.fsx.edu.business.operation.sender.KafkaSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(path = "api/kafkaHome")
@RequiredArgsConstructor
public class MessageController {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaSenderService kafkaSenderService;

    @PostMapping(path = "publish")
    public void publish(@RequestBody MessageRequest request) {
        kafkaTemplate.send(KafkaTopicName.FSX_TOPIC, request.message());
    }

    @GetMapping(path = "send")
    public void send() throws ExecutionException, InterruptedException {
        kafkaSenderService.send();
    }

    @GetMapping(path = "asyncSend")
    public void asyncSend() {
        kafkaSenderService.asyncSend();
    }

    @GetMapping(path = "asyncSendWithoutCallBack")
    public void asyncSendWithoutCallBack() {
        kafkaSenderService.asyncSendWithoutCallBack();
    }
}