package com.lcl.galaxy.netty.stick;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

//todo 服务端 handler
public class MyServerHandler extends SimpleChannelInboundHandler<MyMessageProtocol> {
    private int count;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyMessageProtocol msg) throws Exception {
        System.out.println("====服务端接收到消息如下====");
        System.out.println("长度=" + msg.getLen());
        System.out.println("内容=" + new String(msg.getContent(), CharsetUtil.UTF_8));
        System.out.println("服务端接收到消息包数量=" + (++this.count));
    }
}


