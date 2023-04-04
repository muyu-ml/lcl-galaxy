package com.lcl.galaxy.rpc.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpServerChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class NettyRpcServer {
    public static void main(String[] args) {
        new NettyRpcServer().start(8111);
    }

    NioEventLoopGroup worker;
    NioEventLoopGroup boss;

    private void start(int port) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        worker = new NioEventLoopGroup();
        boss = new NioEventLoopGroup();
        serverBootstrap.group(worker, boss);
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>(){
            @Override
            public void initChannel(SocketChannel socketChannel) throws Exception {
                ChannelPipeline channelPipeline = socketChannel.pipeline();
                channelPipeline.addLast(new ObjectEncoder());
                channelPipeline.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
                channelPipeline.addLast(new ServerProxyHandler());
            }
        });
        try {
            serverBootstrap.bind(port).sync().channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            worker.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }

}
