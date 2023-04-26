package com.fsx.edu.business.operation.sender;


import com.fsx.edu.business.codes.KafkaTopicName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;


@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaSenderServiceImpl implements KafkaSenderService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public KafkaSenderService send() throws ExecutionException, InterruptedException {
        SendResult<String, String> stringSendResult =
                kafkaTemplate
                        .send(KafkaTopicName.FSX_TOPIC, "create user event")
                        .get();
        RecordMetadata recordMetadata = stringSendResult.getRecordMetadata();

        log.info(String.format("partition -> %s", recordMetadata.partition()));
        log.info(String.format("timestamp -> %s", recordMetadata.timestamp()));
        log.info(String.format("offset-> %s", recordMetadata.offset()));
        return this;
    }

    @Override
    public KafkaSenderService asyncSend() {
        kafkaTemplate
                .send(KafkaTopicName.FSX_TOPIC, "create user event")
                .addCallback(new ListenableFutureCallback<>() {
                    @Override
                    public void onSuccess(SendResult<String, String> stringSendResult) {
                        RecordMetadata recordMetadata = stringSendResult.getRecordMetadata();
                        log.info(String.format("partition -> %s", recordMetadata.partition()));
                        log.info(String.format("timestamp -> %s", recordMetadata.timestamp()));
                        log.info(String.format("offset-> %s", recordMetadata.offset()));
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        log.info("error", throwable);
                    }
                });
        return this;
    }

    @Override
    public KafkaSenderService asyncSendWithoutCallBack() {
        kafkaTemplate.send(KafkaTopicName.FSX_TOPIC, "create user event");
        return this;
    }
}
