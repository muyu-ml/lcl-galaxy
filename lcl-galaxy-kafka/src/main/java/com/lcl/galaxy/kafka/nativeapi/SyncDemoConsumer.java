package com.lcl.galaxy.kafka.nativeapi;

import lombok.Data;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Properties;

@Data
public class SyncDemoConsumer {
    private KafkaConsumer<Integer, String> consumer;

    public SyncDemoConsumer(){
        Properties properties = new Properties();
        properties.put("bootstrap.servers","192.168.206.131:9092,192.168.206.132:9092,192.168.206.133:9092");
        properties.put("group.id", "mygroup1");
        properties.put("enable.auto.commit",  "false");
        /*properties.put("max.poll.records", "500");
        properties.put("auto.commit.interval,ms","1000");
        properties.put("session.timeout.ms","30000");
        properties.put("heartbeat.interval.ms","10000");
        properties.put("auto.offset.reset","earliest");*/
        properties.put("key.deserializer","org.apache.kafka.common.serialization.IntegerDeserializer");
        properties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        this.consumer = new KafkaConsumer<Integer, String>(properties);

    }
}
