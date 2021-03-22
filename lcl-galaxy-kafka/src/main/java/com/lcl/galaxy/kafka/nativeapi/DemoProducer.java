package com.lcl.galaxy.kafka.nativeapi;

import lombok.Data;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;

@Data
public class DemoProducer {
    private KafkaProducer<Integer, String> producer;

    public DemoProducer(){
        Properties properties = new Properties();
        properties.put("bootstrap.servers","8.131.245.53:9092,8.131.245.53:9093,8.131.245.53:9094");
        properties.put("key.serializer","org.apache.kafka.common.serialization.IntegerSerializer");
        properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        this.producer = new KafkaProducer<Integer, String>(properties);
    }

    public Future<RecordMetadata> send(ProducerRecord<Integer, String> record) {
        return producer.send(record, (Callback)null);
    }


}
