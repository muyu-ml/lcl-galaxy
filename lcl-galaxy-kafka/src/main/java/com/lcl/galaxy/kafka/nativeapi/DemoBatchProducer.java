package com.lcl.galaxy.kafka.nativeapi;

import lombok.Data;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;

@Data
public class DemoBatchProducer {
    private KafkaProducer<Integer, String> producer;

    public DemoBatchProducer(){
        Properties properties = new Properties();
        properties.put("bootstrap.servers","192.168.206.131:9092,192.168.206.132:9092,192.168.206.133:9092");
        properties.put("key.serializer","org.apache.kafka.common.serialization.IntegerSerializer");
        properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("batch.size",16384);
        properties.put("linger",50);
        this.producer = new KafkaProducer<Integer, String>(properties);
    }

    public Future<RecordMetadata> send(ProducerRecord<Integer, String> record) {
        return producer.send(record, (Callback)null);
    }


}
