package com.lcl.galaxy.netty.webs;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebSocketServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) {
        ChannelPipeline pipeline = channel.pipeline();
        //因为基于http协议，使用http的编码和解码器
        pipeline.addLast(new HttpServerCodec())// http 编解器
                /*
                    说明
                    1. http数据在传输过程中是分段, HttpObjectAggregator ，就是可以将多个段聚合
                    2. 这就是为什么，当浏览器发送大量数据时，就会发出多次http请求
                     */
                .addLast("httpAggregator", new HttpObjectAggregator(512 * 1024))
                //是以块方式写，添加ChunkedWriteHandler处理器,支持异步发送大的码流(大的文件传输),但不占用过多的内存，防止java内存溢出
                .addLast("http-chunked", new ChunkedWriteHandler())
                //WebSocketServerProtocolHandler 核心功能是将 http协议升级为 ws协议 , 保持长连接
                .addLast(new WebSocketServerProtocolHandler("/websocket66"))
                .addLast(new WebSocketRequestHandler());// 请求处理器
    }
}
