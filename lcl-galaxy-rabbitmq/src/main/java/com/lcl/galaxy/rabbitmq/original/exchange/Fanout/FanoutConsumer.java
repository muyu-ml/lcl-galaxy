package com.lcl.galaxy.rabbitmq.original.exchange.Fanout;

import com.rabbitmq.client.*;

import java.io.IOException;

public class FanoutConsumer {

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
        String exchangeName = "fanout_exchange";
        String exchangeType = "fanout";
        channel.exchangeDeclare(exchangeName, exchangeType, true);

        // 5. 声明&绑定队列
        String queueName = channel.queueDeclare().getQueue();
        // 路由键为空
        String routingKey = "abcsbssb";
        channel.queueBind(queueName, exchangeName, routingKey);

        // 5. 消费消息
        while (true){

            boolean autoAck = false;
            String consumerTag = "";

            channel.basicConsume(queueName, autoAck, consumerTag, new DefaultConsumer(channel){

                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                    // 获取 routingKey & contentType
                    String routingKey = envelope.getRoutingKey();
                    String contentType = properties.getContentType();
                    System.out.println("消费的 Routing Key：" + routingKey + " \n消费的 Content Type：" + contentType);

                    // 获取传送标签
                    long deliveryTag = envelope.getDeliveryTag();

                    // 确认消息
                    channel.basicAck(deliveryTag, false);

                    System.out.println("消费的 Body：");
                    String bodyMsg = new String(body, "UTF-8");
                    System.out.println(bodyMsg);
                }
            });
        }
    }
}
