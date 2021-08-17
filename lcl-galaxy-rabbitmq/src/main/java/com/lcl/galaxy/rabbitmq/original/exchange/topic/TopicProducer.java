package com.lcl.galaxy.rabbitmq.original.exchange.topic;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


public class TopicProducer {

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.124.8");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("lcl");
        factory.setPassword("123456");
        // 2. 创建一个 Connection
        Connection conn = factory.newConnection();
        // 3. 获取一个信道 Channel
        Channel channel = conn.createChannel();

        // 4. 声明交换机
        String exchangeName = "topic_exchange";
        String exchangeType = "topic";
        channel.exchangeDeclare(exchangeName, exchangeType, true);

        // 5. 发布消息
        String msg = "hello, topic_exchange";
        String routingKey1 = "hello.cuihua";
        String routingKey2 = "hello.cuicui";
        String routingKey3 = "hello.huahua.didi";

        channel.basicPublish(exchangeName, routingKey1, null, msg.getBytes());
        channel.basicPublish(exchangeName, routingKey2, null, msg.getBytes());
        channel.basicPublish(exchangeName, routingKey3, null, msg.getBytes());

        // 6. 关闭资源
        //channel.close();
        //connection.close();

    }

}
