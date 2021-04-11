package com.lcl.galaxy.kafka.boot;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/boot")
@Slf4j
public class BootProducerApi {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Value("${kafka.topic}")
    private String topic;

    @GetMapping("/send")
    public void sendMsg() throws Exception {
        String cityName = "LY";
        for(int i=0; i<50; i++) {
            ProducerRecord<Integer,String> record = new ProducerRecord<>(topic,cityName + i*1000);
            ListenableFuture<SendResult> future = kafkaTemplate.send(record);
            RecordMetadata recordMetadata = future.get().getRecordMetadata();
            log.info("producer=======【{}】=======【{}】", i+1, recordMetadata.offset());
            log.info("producer=======【{}】=======【{}】", i+1, recordMetadata.partition());
            log.info("producer=======【{}】=======【{}】", i+1, recordMetadata.timestamp());
            log.info("producer=======【{}】=======【{}】", i+1, recordMetadata.topic());
        }
    }
}
