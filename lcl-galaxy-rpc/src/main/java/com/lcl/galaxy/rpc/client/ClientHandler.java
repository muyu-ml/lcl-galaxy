package com.lcl.galaxy.rpc.client;

import com.lcl.galaxy.rpc.protocol.Protocol;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientHandler extends SimpleChannelInboundHandler<Object> {

    private Protocol protocol;
    private Object result;
    private Channel channel;

    public ClientHandler(Protocol protocol){
        this.protocol = protocol;
    }

    public Object getResult(){
        return result;
    }

    @Override
    public void channelActive(ChannelHandlerContext cxt) {
        channel = cxt.channel();
        channel.writeAndFlush(protocol);
    }

    @Override
    protected synchronized void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        this.result = o;
        System.out.println("ClientHandler.channelRead0=======" + result);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    }
}
