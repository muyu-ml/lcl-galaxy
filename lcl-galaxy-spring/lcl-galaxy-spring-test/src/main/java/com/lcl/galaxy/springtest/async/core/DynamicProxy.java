package com.lcl.galaxy.springtest.async.core;

import com.lcl.galaxy.springtest.async.api.AsyncProxy;
import com.lcl.galaxy.springtest.async.api.AsyncResult;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.Future;

public class DynamicProxy implements InvocationHandler, AsyncProxy {

    //被代理的对象
    private final Object target;

    public DynamicProxy(Object target){
        this.target = target;
    }

    @Override
    public Object getProxy() {
        InvocationHandler handler = new DynamicProxy(target);
        return Proxy.newProxyInstance(handler.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //提交到Executor并返回结果
        return (FutureBasedAsyncResult)ThreadPoolBasedAsyncExecutor.submit(target, method, args).getResult();
    }
}
