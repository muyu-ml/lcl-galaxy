package com.lcl.galaxy.springtest.proxy.performance.test;

import com.lcl.galaxy.springtest.proxy.model.Account;
import com.lcl.galaxy.springtest.proxy.performance.config.CglibProxyAppConfig;
import com.lcl.galaxy.springtest.proxy.performance.config.JDKProxyAppConfig;
import com.lcl.galaxy.springtest.proxy.service.AccountService;
import com.lcl.galaxy.springtest.proxy.service.impl.AccountServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestProxyPerformance1 {
    public static void main(String[] args) {
        long start = System.nanoTime();
        int countOfObjects = 500;
        // 不用代理
        AccountService[] unProxy = new AccountService[countOfObjects];
        for (int i=0; i<countOfObjects; i++){
            unProxy[i] = getUnProxy();
        }
        invokeTargetObjects(unProxy);
        long unproxyTime = System.nanoTime()- start;



        // 使用 cglib
        start = System.nanoTime();
        AccountService[] cglibProxy = new AccountService[countOfObjects];
        for (int i=0; i<countOfObjects; i++){
            cglibProxy[i] = getCglibProxy();
        }
        invokeTargetObjects(cglibProxy);
        long cglibProxyTime = System.nanoTime()- start;



        // 使用 jdk
        start = System.nanoTime();
        AccountService[] jdkProxy = new AccountService[countOfObjects];
        for (int i=0; i<countOfObjects; i++){
            jdkProxy[i] = getJdkProxy();
        }
        invokeTargetObjects(jdkProxy);
        long jdkProxyTime = System.nanoTime()- start;


        System.out.println("unProxy：" + unproxyTime);
        System.out.println("cglibProxy：" + cglibProxyTime);
        System.out.println("jdkProxy：" + jdkProxyTime);
    }

    public static AccountService getUnProxy(){
        return new AccountServiceImpl();
    }

    public static AccountService getJdkProxy(){
        return new AnnotationConfigApplicationContext(JDKProxyAppConfig.class).getBean(AccountService.class);
    }

    public static AccountService getCglibProxy(){
        return new AnnotationConfigApplicationContext(CglibProxyAppConfig.class).getBean(AccountService.class);
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
