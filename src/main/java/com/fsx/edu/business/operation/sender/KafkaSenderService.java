package com.fsx.edu.business.operation.sender;

import java.util.concurrent.ExecutionException;

public interface KafkaSenderService {

    KafkaSenderService send() throws ExecutionException, InterruptedException;

    KafkaSenderService asyncSend();

    KafkaSenderService asyncSendWithoutCallBack();
}