package com.lcl.galaxy.rabbitmq.original.exchange.Fanout;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


public class FanoutProducer {

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

        String msg = "hello, fanout_exchange";
        // 不设置路由键，或者随便设置
        String routingKey = "";
        channel.basicPublish(exchangeName, routingKey, null, msg.getBytes());

        // 6. 关闭资源
        //channel.close();
        //connection.close();

    }

}
