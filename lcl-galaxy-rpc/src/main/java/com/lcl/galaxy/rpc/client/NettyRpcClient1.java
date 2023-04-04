package com.lcl.galaxy.rpc.client;

import com.lcl.galaxy.rpc.protocol.Protocol;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class NettyRpcClient1 {
    private NioEventLoopGroup work;
    //private ClientProxyHandler clientProxyHandler;

    public void start(String host, int port){
        Protocol protocol = new Protocol();
        protocol.setInterfaceName("com.lcl.galaxy.rpc.service.UserService");
        protocol.setMethodName("getUserNameByCode");
        Class[] paramterType = {String.class};
        Object[] paramters = {"1"};
        protocol.setParameters(paramters);
        protocol.setParamsTypes(paramterType);

        final ClientHandler clientHandler = new ClientHandler(protocol);
        Bootstrap bootstrap = new Bootstrap();
        work = new NioEventLoopGroup();
        bootstrap.group(work).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                ChannelPipeline channelPipeline = socketChannel.pipeline();
                channelPipeline.addLast(new ObjectEncoder());
                channelPipeline.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
                channelPipeline.addLast(clientHandler);
            }
        });
        try {
            bootstrap.connect(host, port).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("NettyRpcClient1.start======== " + clientHandler.getResult());
    }


}
