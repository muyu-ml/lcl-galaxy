package lcl.galaxy.jvm.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class DynamicTest {
    public static void main(String[] args) {
        OrderService orderService = new OrderServiceImpl();
        InvocationHandler invocationHandler = new DynamicProxy(orderService);
        OrderService proxy = (OrderService)Proxy.newProxyInstance(invocationHandler.getClass().getClassLoader(), orderService.getClass().getInterfaces(), invocationHandler);
        String s = proxy.getOrderName("lcl");

    }
}
