package com.lcl.galaxy.springtest.testProxy.testp;

import com.lcl.galaxy.springtest.model.Account;
import com.lcl.galaxy.springtest.service.AccountService;
import com.lcl.galaxy.springtest.service.impl.AccountServiceImpl;
import com.lcl.galaxy.springtest.testProxy.advice.MyInvocationHandler;
import com.lcl.galaxy.springtest.testProxy.advice.MyMethodIntercepor;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        Account account1 = new Account(123, "zhangsan");
        Account account2 = new Account(345, "lisi");
        AccountService proxy = new ProxyTest().getCglibProxyTest();
        proxy.doAccountTransaction(account1, account2, 100);
    }

    public Object getJdkProxyTest(){
        AccountService service = new AccountServiceImpl();
        AccountService proxy = (AccountService) Proxy.newProxyInstance(service.getClass().getClassLoader(), new Class[] {AccountService.class}, new MyInvocationHandler(service));
        return proxy;
    }

    public AccountService getCglibProxyTest(){
        Enhancer enhancer = new Enhancer();
        AccountService service = new AccountServiceImpl();
        enhancer.setSuperclass(service.getClass());
        enhancer.setCallback(new MyMethodIntercepor());
        AccountService proxy = (AccountService)enhancer.create();
        return proxy;
    }
}
