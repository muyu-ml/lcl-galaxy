package com.lcl.galaxy.springtest.proxy.testProxy.advice;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class MyMethodIntercepor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("MyMethodIntercepor.intercept");
        Object obj = methodProxy.invokeSuper(o, objects);
        return obj;
    }
}
