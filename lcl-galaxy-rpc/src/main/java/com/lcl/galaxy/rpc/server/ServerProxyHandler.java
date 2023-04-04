package com.lcl.galaxy.rpc.server;

import com.lcl.galaxy.rpc.protocol.Protocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.lang.reflect.Method;

public class ServerProxyHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        Protocol protocol = (Protocol) o;
        // 获取当前实现类
        String className = protocol.getInterfaceName()+"Impl";
        String methodName = protocol.getMethodName();
        Class<?>[] paramterTypes = protocol.getParamsTypes();
        Object[] paramValues = protocol.getParameters();

        // 反射获取实现类
        Class<?> aClass = Class.forName(className);
        Object bean = aClass.newInstance();
        Method method = aClass.getDeclaredMethod(methodName, paramterTypes);

        // 调用
        Object result = method.invoke(bean, paramValues);
        channelHandlerContext.writeAndFlush(result);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    }
}
