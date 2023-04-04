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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class NettyRpcClient3 {
    private NioEventLoopGroup work;
    private ClientProxyHandler clientProxyHandler;

    public void start(String host, int port){

        Bootstrap bootstrap = new Bootstrap();
        work = new NioEventLoopGroup();
        clientProxyHandler = new ClientProxyHandler();
        bootstrap.group(work).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                ChannelPipeline channelPipeline = socketChannel.pipeline();
                channelPipeline.addLast(new ObjectEncoder());
                channelPipeline.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
                channelPipeline.addLast(clientProxyHandler);
            }
        });
        try {
            bootstrap.connect(host, port).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public <T> T proxy(final Class<T> target){
        return (T) Proxy.newProxyInstance(target.getClassLoader(), new Class[]{target}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Protocol protocol = new Protocol();
                protocol.setInterfaceName(target.getName());
                protocol.setMethodName(method.getName());
                protocol.setParameters(args);
                protocol.setParamsTypes(method.getParameterTypes());
                System.out.println("NettyRpcClient3.invoke======" + protocol);
                clientProxyHandler.setProtocol(protocol);
                FutureTask<Object> futureTask = new FutureTask<>(clientProxyHandler);
                new Thread(futureTask).start();
                return futureTask.get();
            }
        });
    }

}
