package com.lcl.galaxy.kafka.nativeapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BootConsumer {

    @KafkaListener(topics = "${kafka.topic}")
    public void onMsg(String message){
        log.info("消费消息======【{}】", message);
    }
}
