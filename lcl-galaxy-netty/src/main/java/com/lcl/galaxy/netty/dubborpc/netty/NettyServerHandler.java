package com.lcl.galaxy.netty.dubborpc.netty;

import com.lcl.galaxy.netty.dubborpc.api.CityService;
import com.lcl.galaxy.netty.dubborpc.api.Constant;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    private CityService cityService = null;


    public NettyServerHandler(CityService cityService ){
        this.cityService = cityService;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //获取客户端发送的消息，并调用服务
        System.out.println("msg=" + msg);
        //客户端在调用服务器的api时，我们需要定义一个协议
        //比如我们要求 每次发消息是都必须以某个字符串开头 "cityService#name##你好"
        if (msg.toString().startsWith(Constant.protocolName)) {

            String result = cityService.getName(msg.toString().substring(Constant.protocolName.length()));
            ctx.writeAndFlush(result);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
