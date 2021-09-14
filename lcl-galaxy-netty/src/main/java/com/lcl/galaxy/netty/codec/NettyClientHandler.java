package com.lcl.galaxy.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    //当通道就绪就会触发该方法
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

//        MyDataInfo.MyMessage message =
//                MyDataInfo.MyMessage.newBuilder().setDataType( MyDataInfo.MyMessage.DataType.StudentType)
//                .setStudent(MyDataInfo.Student.newBuilder().setId(6).setName("北京").build()).build();
//        ctx.writeAndFlush(message);
        MyDataInfo.MyMessage message =
                MyDataInfo.MyMessage.newBuilder().setDataType( MyDataInfo.MyMessage.DataType.WorkerType)
                        .setWorker(MyDataInfo.Worker.newBuilder().setAge(20).setName("杭州").build()).build();
        ctx.writeAndFlush(message);
    }

    //当通道有读取事件时，会触发
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("服务器回复的消息:" + buf.toString(CharsetUtil.UTF_8));

        System.out.println("客户端本地的地址： " + ctx.channel().localAddress());
        System.out.println("服务器的地址： " + ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
