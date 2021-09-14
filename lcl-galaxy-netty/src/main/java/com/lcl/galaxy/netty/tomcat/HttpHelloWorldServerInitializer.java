package com.lcl.galaxy.netty.tomcat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.HttpServerExpectContinueHandler;


public class HttpHelloWorldServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    public void initChannel(SocketChannel ch) {
        ChannelPipeline p = ch.pipeline();
        /**
         * 或者使用HttpRequestDecoder & HttpResponseEncoder
         * HttpRequestDecoder 即把 ByteBuf 解码到 HttpRequest 和 HttpContent。
         * HttpResponseEncoder 即把 HttpResponse 或 HttpContent 编码到 ByteBuf。
         * HttpServerCodec 即 HttpRequestDecoder 和 HttpResponseEncoder 的结合。
         */
        p.addLast(new HttpServerCodec());
        /**
         * 在处理POST消息体时需要加上
         */
        p.addLast(new HttpObjectAggregator(1024*1024));
        //这个方法的作用是: http 100-continue用于客户端在发送POST数据给服务器前，
        // 征询服务器情况，看服务器是否处理POST的数据，
        // 如果不处理，客户端则不上传POST数据，如果处理，则POST上传数据。
        // 在现实应用中，通过在POST大数据时，才会使用100-continue协议
        //
        p.addLast(new HttpServerExpectContinueHandler());
        p.addLast(new HttpHelloWorldServerHandler());
    }
}
