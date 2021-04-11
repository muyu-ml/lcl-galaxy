package com.lcl.galaxy.kafka.nativeapi;

import lombok.Data;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Properties;

@Data
public class DemoConsumer{
    private KafkaConsumer<Integer, String> consumer;

    public DemoConsumer(){
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.206.131:9092,192.168.206.132:9092,192.168.206.133:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "mygroup1");
        //properties.put("enable.auto.commit",  "true");
        properties.put("max.poll.records", "500");
        properties.put("auto.commit.interval,ms","1000");
        properties.put("session.timeout.ms","30000");
        properties.put("heartbeat.interval.ms","10000");
        properties.put("auto.offset.reset","earliest");
        properties.put("key.deserializer","org.apache.kafka.common.serialization.IntegerDeserializer");
        properties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");

        //设置是否手动提交及最大提交数
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,false);
        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,10);

        this.consumer = new KafkaConsumer<Integer, String>(properties);
    }
}
