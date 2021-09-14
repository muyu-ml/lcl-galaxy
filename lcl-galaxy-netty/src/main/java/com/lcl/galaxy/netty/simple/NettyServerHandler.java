package com.lcl.galaxy.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.concurrent.TimeUnit;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    private static final EventExecutorGroup group = new DefaultEventExecutorGroup(8);

    /**
     * 1. ChannelHandlerContext ctx:上下文对象, 含有 管道pipeline , 通道channel, 地址
     * 2. Object msg: 就是客户端发送的数据 默认Object
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channelReadThread:"+Thread.currentThread().getName());
        ctx.writeAndFlush(Unpooled.copiedBuffer(System.currentTimeMillis() + "hello, Client No 2 ", CharsetUtil.UTF_8));
        /*group.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("groupThread:"+Thread.currentThread().getName());
                ctx.writeAndFlush(Unpooled.copiedBuffer("hello, Client No 2 ", CharsetUtil.UTF_8));
            }
        });*/
        System.out.println("go on ...");
    }




    //数据读取完毕
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //writeAndFlush 是 write + flush
        //将数据写入到缓存，并刷新
        //一般讲，我们对这个发送的数据进行编码
        System.out.println("channelReadCompleteThread:"+Thread.currentThread().getName());
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, Client No 1 ", CharsetUtil.UTF_8));
    }

    //处理异常, 一般是需要关闭通道
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    //读取数据实际(这里我们可以读取客户端发送的消息)

    public void channelRead2(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("===================");

        System.out.println("server ctx =" + ctx);
        System.out.println("服务器读取线程 " + Thread.currentThread().getName());
        System.out.println("看看channel 和 pipeline的关系");
        System.out.println("对应的channel="+ctx.channel());
        System.out.println("对应的pipeline="+ctx.pipeline());
        System.out.println("通过pipeline获取channel="+ctx.pipeline().channel());
        System.out.println("通过channel获取pipeline="+ctx.channel().pipeline());

        System.out.println("当前ctx的handler="+ctx.handler());

        //将 msg 转成一个 ByteBuf
        //ByteBuf 是 Netty 提供的，不是 NIO 的 ByteBuffer.
        ByteBuf buf = (ByteBuf) msg;
//        System.out.println("客户端发送消息是:" + buf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端发送消息是:"+buf);
        System.out.println("客户端地址:" + ctx.channel().remoteAddress());
    }


    public void channelRead12(ChannelHandlerContext ctx, Object msg) throws Exception {
        //Thread.sleep(10 * 1000);
        //ctx.writeAndFlush(Unpooled.copiedBuffer(System.currentTimeMillis() + "hello, Client No 2 ", CharsetUtil.UTF_8));
        System.out.println("channelReadThread:"+Thread.currentThread().getName());
        // 比如这里我们有一个非常耗时长的业务-> 异步执行 -> 提交该channel 对应的
        // NIOEventLoop 的 taskQueue中,
        // 解决方案1 用户程序自定义的普通任务
        /*ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(10 * 1000);
                    System.out.println("eventLoop().execute Thread1:"+Thread.currentThread().getName());
                    //请求讲当前的msg 通过ChannelPipeline 写入数据到目标Channel 中。值得注意的是：write 操作只是将消息存入到消息发送环形数组中，并没有真正被发送，只有调用flush 操作才会被写入到Channel 中，发送给对方。
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello, Client No 2 ", CharsetUtil.UTF_8));
                    System.out.println("Client No 2 success,"+Thread.currentThread().getName());
                } catch (Exception ex) {
                    System.out.println("发生异常" + ex.getMessage());
                }
            }
        });

        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(5 * 1000);
                    System.out.println("eventLoop().execute Thread2:"+Thread.currentThread().getName());
                    //请求讲当前的msg 通过ChannelPipeline 写入数据到目标Channel 中。值得注意的是：write 操作只是将消息存入到消息发送环形数组中，并没有真正被发送，只有调用flush 操作才会被写入到Channel 中，发送给对方。
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello, Client No 3 ", CharsetUtil.UTF_8));
                    System.out.println("Client No 3 success,"+Thread.currentThread().getName());
                } catch (Exception ex) {
                    System.out.println("发生异常" + ex.getMessage());
                }
            }
        });*/




        //解决方案2 : 用户自定义定时任务 -》 该任务是提交到 scheduleTaskQueue中


        ctx.channel().eventLoop().schedule(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(5 * 1000);
                    System.out.println("channelReadThread3:"+Thread.currentThread().getName());
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello, Client No 4", CharsetUtil.UTF_8));
                    System.out.println("channel code=" + ctx.channel().hashCode());
                } catch (Exception ex) {
                    System.out.println("发生异常" + ex.getMessage());
                }
            }
        }, 5, TimeUnit.SECONDS);

        System.out.println(System.currentTimeMillis() + "go on ...");

    }

//    private static final EventExecutorGroup group = new DefaultEventExecutorGroup(8);

    //@Override
    public void channelRead11(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channelReadThread:"+Thread.currentThread().getName());
        group.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5*1000);
                    System.out.println("groupSubmitThread:"+Thread.currentThread().getName());

                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello, Client No 2 ", CharsetUtil.UTF_8));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("go on ...");
    }

    //abstract class AbstractChannelHandlerContext implements ChannelHandlerContext, ResourceLeakHint { 788
}
