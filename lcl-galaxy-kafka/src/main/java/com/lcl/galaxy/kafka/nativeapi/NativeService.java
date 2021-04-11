package com.lcl.galaxy.kafka.nativeapi;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
public class NativeService {

    private final String topic = "cities";

    public void sendMsg() throws Exception {

        DemoProducer producer = new DemoProducer();
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
        log.info("=====================【{}】", recordMetadata.offset());
        log.info("=====================【{}】", recordMetadata.partition());
        log.info("=====================【{}】", recordMetadata.timestamp());
        log.info("=====================【{}】", recordMetadata.topic());
    }





    public void sendMsg1() throws Exception {
        DemoBatchProducer producer = new DemoBatchProducer();
        int partition = 0;
        int key = 1;
        String cityName = "beijing";
        for(int i=0; i<50; i++) {
            ProducerRecord<Integer,String> record = new ProducerRecord<>(topic,cityName + i*1000);
            Future<RecordMetadata> future = producer.send(record);
            RecordMetadata recordMetadata = future.get();
            log.info("【{}】=====================【{}】", i+1, recordMetadata.offset());
            log.info("【{}】=====================【{}】", i+1, recordMetadata.partition());
            log.info("【{}】=====================【{}】", i+1, recordMetadata.timestamp());
            log.info("【{}】=====================【{}】", i+1, recordMetadata.topic());
        }
    }


    public void autoDoWork(){
        DemoConsumer consumer = new DemoConsumer();
        consumer.getConsumer().subscribe(Collections.singletonList(topic));
        ConsumerRecords<Integer, String> records = consumer.getConsumer().poll(1000);
        for (ConsumerRecord<Integer, String> consumerRecord: records) {
            log.info("============topic={},partition={},key={},value={}===============",consumerRecord.topic(), consumerRecord.partition(), consumerRecord.key(), consumerRecord.value());
        }
    }

    public void doWorkDemo(){
        DemoConsumer consumer = new DemoConsumer();
        consumer.getConsumer().subscribe(Collections.singletonList(topic));
        ConsumerRecords<Integer, String> records = consumer.getConsumer().poll(1000);
        for (ConsumerRecord<Integer, String> consumerRecord: records) {
            log.info("============topic={},partition={},key={},value={}===============",consumerRecord.topic(), consumerRecord.partition(), consumerRecord.key(), consumerRecord.value());
            //同步手动提交
            consumer.getConsumer().commitSync();
            //带回调功能的异步手动提交
            consumer.getConsumer().commitAsync((offsets, e) -> {
                if(e != null){
                    log.info("提交失败，offsets=【{}】，失败原因【{}】", offsets, e);
                    //异步同步手动提交
                    consumer.getConsumer().commitSync();
                }
            });
        }
    }


    public void syncDoWork(){
        DemoConsumer consumer = new DemoConsumer();
        consumer.getConsumer().subscribe(Collections.singletonList(topic));
        ConsumerRecords<Integer, String> records = consumer.getConsumer().poll(1000);
        for (ConsumerRecord<Integer, String> consumerRecord: records) {
            log.info("============topic={},partition={},key={},value={}===============",consumerRecord.topic(), consumerRecord.partition(), consumerRecord.key(), consumerRecord.value());
            consumer.getConsumer().commitSync();
        }
    }

    public void asyncDoWork(){
        DemoConsumer consumer = new DemoConsumer();
        consumer.getConsumer().subscribe(Collections.singletonList(topic));
        ConsumerRecords<Integer, String> records = consumer.getConsumer().poll(1000);
        for (ConsumerRecord<Integer, String> consumerRecord: records) {
            log.info("============topic={},partition={},key={},value={}===============",consumerRecord.topic(), consumerRecord.partition(), consumerRecord.key(), consumerRecord.value());
            consumer.getConsumer().commitAsync((offsets, e) -> {
                if(e != null){
                    log.info("提交失败，offsets=【{}】，失败原因【{}】", offsets, e);
                }
            });
        }
    }

    public void syncasyncDoWork(){
        DemoConsumer consumer = new DemoConsumer();
        consumer.getConsumer().subscribe(Collections.singletonList(topic));
        ConsumerRecords<Integer, String> records = consumer.getConsumer().poll(1000);
        for (ConsumerRecord<Integer, String> consumerRecord: records) {
            log.info("============topic={},partition={},key={},value={}===============",consumerRecord.topic(), consumerRecord.partition(), consumerRecord.key(), consumerRecord.value());
            consumer.getConsumer().commitAsync((offsets, e) -> {
                if(e != null){
                    log.info("提交失败，offsets=【{}】，失败原因【{}】", offsets, e);
                    consumer.getConsumer().commitSync();
                }
            });
        }
    }

}
