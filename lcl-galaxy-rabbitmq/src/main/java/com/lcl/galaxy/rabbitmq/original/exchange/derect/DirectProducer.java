package com.lcl.galaxy.rabbitmq.original.exchange.derect;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class DirectProducer {

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
        //声明交换机
        String exchangeName = "direct_exchange";
        String exchangeType = "direct";
        channel.exchangeDeclare(exchangeName, exchangeType, true);
        //发布消息
        String msg = "hello, direct_exchange2";
        String routingKey = "msg_direct";
        channel.basicPublish(exchangeName, routingKey, null, msg.getBytes());
        // 6. 关闭资源
        //channelUtil.close();

    }

}
