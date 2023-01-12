package com.lcl.galaxy.springtest;

import com.lcl.galaxy.springtest.proxy.model.Account;
import com.lcl.galaxy.springtest.proxy.performance.config.CglibProxyAppConfig;
import com.lcl.galaxy.springtest.proxy.performance.config.JDKProxyAppConfig;
import com.lcl.galaxy.springtest.proxy.service.AccountService;
import com.lcl.galaxy.springtest.proxy.service.impl.AccountServiceImpl;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProxyTest {

    @Test
    public void testAop() {
        int countOfObjects = 500;
        // 不用代理
        AccountService[] unProxy = new AccountServiceImpl[countOfObjects];
        for (int i=0; i<countOfObjects; i++){
            unProxy[i] = new AccountServiceImpl();
        }

        // 使用 cglib
        AccountService[] cglibProxy = new AccountServiceImpl[countOfObjects];
        for (int i=0; i<countOfObjects; i++){
            cglibProxy[i] = new AnnotationConfigApplicationContext(CglibProxyAppConfig.class).getBean(AccountService.class);
        }

        // 使用 jdk
        AccountService[] jdkProxy = new AccountServiceImpl[countOfObjects];
        for (int i=0; i<countOfObjects; i++){
            jdkProxy[i] = new AnnotationConfigApplicationContext(JDKProxyAppConfig.class).getBean(AccountService.class);
        }

        long useTime = invokeTargetObjects(unProxy);
        System.out.println("noProxy：" + useTime);
        useTime = invokeTargetObjects(jdkProxy);
        System.out.println("jdkProxy：" + useTime);
        useTime = invokeTargetObjects(cglibProxy);
        System.out.println("cglibProxy：" + useTime);
    }

    private static long invokeTargetObjects(AccountService[] accountServices){
        long start = System.nanoTime();
        Account source = new Account(123, "zhangsan");
        Account dest = new Account(345, "lisi");
        for(int i=0; i<accountServices.length; i++){
            accountServices[i].doAccountTransaction(source, dest, 100);
        }
        return System.nanoTime() - start;
    }
}
