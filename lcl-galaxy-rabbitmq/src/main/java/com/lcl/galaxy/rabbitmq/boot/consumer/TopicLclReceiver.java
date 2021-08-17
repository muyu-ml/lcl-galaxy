package com.lcl.galaxy.rabbitmq.boot.consumer;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "topic.man.lcl")
public class TopicLclReceiver {
    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("TopicLclReceiver消费者收到消息  : " + testMessage.toString());
    }

}
