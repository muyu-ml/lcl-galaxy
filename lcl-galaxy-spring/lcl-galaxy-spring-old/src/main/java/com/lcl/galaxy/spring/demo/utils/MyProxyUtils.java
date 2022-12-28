package com.lcl.galaxy.spring.demo.utils;

import com.lcl.galaxy.spring.demo.UserServiceImpl;
import com.lcl.galaxy.spring.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Slf4j
public class MyProxyUtils {
    public static UserService getProxyByJdk(final UserService userService){
        //使用proxy类生成代理对象
        UserService proxy = (UserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(),
                new InvocationHandler() {
                    //代理对象方法一执行，invoke方法就会执行一次
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if("save".equals(method.getName())){
                            log.info("=============记录日志==============");
                        }
                        //让service类的save方法正常的执行下去
                        return method.invoke(userService, args);
                    }
                });
        //返回代理对象
        return proxy;
    }

    public static UserService getProxyByCglib(){
        //创建CGLIB核心类
        Enhancer enhancer = new Enhancer();
        //设置父类
        enhancer.setSuperclass(UserServiceImpl.class);
        //设置回调函数
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                if("save".equals(method.getName())){
                    log.info("=============记录日志==============");
                    log.info("=============开启事务==============");
                }
                log.info("=============提交事务志==============");
                return methodProxy.invokeSuper(o, objects);
            }
        });
        //生成代理对象
        UserService proxy = (UserService) enhancer.create();
        return proxy;
    }
}
