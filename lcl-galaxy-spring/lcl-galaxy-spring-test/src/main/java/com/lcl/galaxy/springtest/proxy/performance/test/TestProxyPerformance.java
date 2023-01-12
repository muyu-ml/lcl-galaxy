package com.lcl.galaxy.springtest.proxy.performance.test;

import com.lcl.galaxy.springtest.proxy.model.Account;
import com.lcl.galaxy.springtest.proxy.performance.config.CglibProxyAppConfig;
import com.lcl.galaxy.springtest.proxy.performance.config.JDKProxyAppConfig;
import com.lcl.galaxy.springtest.proxy.service.AccountService;
import com.lcl.galaxy.springtest.proxy.service.impl.AccountServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestProxyPerformance {
    public static void main(String[] args) {
        int countOfObjects = 500;
        // 不用代理
        AccountService[] unProxy = new AccountService[countOfObjects];
        for (int i=0; i<countOfObjects; i++){
            unProxy[i] = new AccountServiceImpl();
        }

        // 使用 cglib
        AccountService[] cglibProxy = new AccountService[countOfObjects];
        for (int i=0; i<countOfObjects; i++){
            cglibProxy[i] = new AnnotationConfigApplicationContext(CglibProxyAppConfig.class).getBean(AccountService.class);
        }

        // 使用 jdk
        AccountService[] jdkProxy = new AccountService[countOfObjects];
        for (int i=0; i<countOfObjects; i++){
            AccountService accountService = new AnnotationConfigApplicationContext(JDKProxyAppConfig.class).getBean(AccountService.class);
            try {
                jdkProxy[i] = accountService;
            }catch (Exception e){
                e.printStackTrace();
                throw e;
            }

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
        long useNanoTime = System.nanoTime() - start;
        return useNanoTime/1000;
    }
}
