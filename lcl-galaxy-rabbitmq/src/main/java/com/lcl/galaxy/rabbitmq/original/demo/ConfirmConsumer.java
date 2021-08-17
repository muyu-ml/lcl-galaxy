package com.lcl.galaxy.rabbitmq.original.demo;

import com.rabbitmq.client.*;

import java.io.IOException;

public class ConfirmConsumer {
    public static void main(String[] args) throws Exception {
        // 1 创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.124.8");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("lcl");
        connectionFactory.setPassword("123456");
        // 2 获取Connection
        Connection connection = connectionFactory.newConnection();
        // 3 通过Connection创建一个新的Channel
        Channel channel = connection.createChannel();
        String exchangeName = "hello_confirm_exchange";
        String routingKey = "confirm.#";
        String queueName = "hello_confirm_queue";
        // 4 声明交换机和队列 然后进行绑定设置, 最后制定路由Key
        channel.exchangeDeclare(exchangeName, "topic", true);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routingKey);
        // 5 创建消费者
//        while (true){
            System.err.println("------- 开始消费 ----------");
            boolean autoAck = false;
            String consumerTag = "";
            channel.basicConsume(queueName, autoAck, consumerTag, new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    // 获取传送标签
                    // long deliveryTag = envelope.getDeliveryTag();
                    // 确认消息
                    // channel.basicAck(deliveryTag, false);
                    System.out.println("消费的 Body：" + new String(body, "UTF-8"));
                }
            });
//        }
    }
}
