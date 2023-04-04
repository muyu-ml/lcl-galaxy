package com.lcl.galaxy.rpc.client;

import com.lcl.galaxy.rpc.protocol.Protocol;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.Callable;

public class ClientProxyHandler extends SimpleChannelInboundHandler<Object> implements Callable {


    private Protocol protocol;
    private Object result;
    private Channel channel;

    public void setProtocol(Protocol protocol){
        this.protocol = protocol;
    }

    @Override
    public void channelActive(ChannelHandlerContext cxt) {
        channel = cxt.channel();
    }

    @Override
    protected synchronized void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        this.result = o;
        notify();
    }


    @Override
    public synchronized Object call() throws Exception {
        channel.writeAndFlush(protocol);
        wait();
        return result;
    }
}
