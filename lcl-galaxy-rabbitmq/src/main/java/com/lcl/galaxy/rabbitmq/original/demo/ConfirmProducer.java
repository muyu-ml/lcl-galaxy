package com.lcl.galaxy.rabbitmq.original.demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

public class ConfirmProducer {
    public static void main(String[] args) throws Exception {

        //1 创建 ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.124.8");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("lcl");
        connectionFactory.setPassword("123456");
        //2 获取 Connection
        Connection connection = connectionFactory.newConnection();
        //3 通过 Connection 创建一个新的 Channel
        Channel channel = connection.createChannel();

        String exchangeName = "hello_confirm_exchange";
        String routingKey = "confirm.save";
        String msg = "Hello Confirm Message";
        //4 指定我们的消息投递模式: 消息的确认模式
        channel.confirmSelect();
        //5 发送一条消息
        channel.basicPublish(exchangeName, routingKey, null, msg.getBytes());
        System.err.println("------- 发送完成 ----------");
        //6 添加一个确认监听
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.err.println("------- 没有 ACK ----------");
            }
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.err.println("------- 收到 ACK -----------");
            }
        });
    }
}
