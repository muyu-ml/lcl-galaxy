package com.lcl.galaxy.kafka.nativeapi;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
public class NativeService {

    public void sendMsg() throws Exception {

        DemoProducer producer = new DemoProducer();
        String topic = "cities";
        int partition = 0;
        int key = 1;
        String cityName = "beijing";
        //指定主题及消息
        ProducerRecord<Integer,String> record = new ProducerRecord<>(topic,cityName);
        //指定主题、key、消息
        //ProducerRecord<Integer,String> record = new ProducerRecord<>(topic,key,cityName);
        //指定主题、partition、key、消息
        //ProducerRecord<Integer,String> record = new ProducerRecord<>(topic,partition,key,cityName);
        Future<RecordMetadata> future = producer.send(record);
        RecordMetadata recordMetadata = future.get();
        log.info("=====================【{}】", JSON.toJSONString(recordMetadata));
    }





    public void sendMsg1() throws Exception {
        DemoBatchProducer producer = new DemoBatchProducer();
        String topic = "cities";
        int partition = 0;
        int key = 1;
        String cityName = "beijing";
        for(int i=0; i<50; i++) {
            ProducerRecord<Integer,String> record = new ProducerRecord<>(topic,cityName + i*1000);
            Future<RecordMetadata> future = producer.send(record);
            RecordMetadata recordMetadata = future.get();
            log.info("=====================【{}】", JSON.toJSONString(recordMetadata));
        }
    }

}
