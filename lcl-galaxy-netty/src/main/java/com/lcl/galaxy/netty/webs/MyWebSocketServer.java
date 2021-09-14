package com.lcl.galaxy.netty.webs;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MyWebSocketServer {
    int port;

    public MyWebSocketServer(int port) {
        this.port = port;
    }

    public void start() {
        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        try {
            bootstrap.group(boss, work)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new WebSocketServerInitializer());

            ChannelFuture f = bootstrap.bind(new InetSocketAddress(port)).sync();
            System.out.println("server started . port : " + port);
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    public void pushMsg(){
        //模拟异步发送推送消息
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
        scheduledThreadPool.scheduleWithFixedDelay(() -> {
            TextWebSocketFrame tws = new TextWebSocketFrame("server push message,this time："+ LocalDateTime.now());
            // 群发
            ChannelSupervise.send2All(tws);
        }, 0,1, TimeUnit.SECONDS);

    }

    public static void main(String[] args) {
        MyWebSocketServer server = new MyWebSocketServer(8081);// 8081为启动端口

//        server.pushMsg();

        server.start();
    }
}
