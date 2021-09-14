package com.lcl.galaxy.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 1. `Netty` 服务器在 `6868` 端口监听，浏览器发出请求 `http://localhost:6868/`
 * 2. 服务器可以回复消息给客户端"Hello",并对特定请求资源进行过滤。
 * 3. 目的：`Netty` 可以做 `Http` 服务开发，并且理解 `Handler` 实例和客户端及其请求的关系。
 */
public class TestHttpServer {

    public static void main(String[] args) throws Exception {

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new TestServerInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind(6868).sync();

            channelFuture.channel().closeFuture().sync();



            //绑定一个端口并且同步,生成了一个ChannelFuture对象
//启动服务器(并绑定端口)
            ChannelFuture cf = serverBootstrap.bind(6668).sync();
//给cf注册监听器，监控我们关心的事件
            cf.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete (ChannelFuture future) throws Exception {

                    if (cf.isSuccess()) {
                        System.out.println("监听端口6668成功");
                    } else {
                        System.out.println("监听端口6668失败");
                    }
                }
            });

        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
