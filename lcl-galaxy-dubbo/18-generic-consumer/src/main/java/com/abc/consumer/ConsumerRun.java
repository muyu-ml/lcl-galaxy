package com.abc.consumer;

import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class ConsumerRun {
    public static void main(String[] args) throws IOException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-consumer.xml");

        GenericService service = (GenericService) ac.getBean("someService");
        
        Object hello = service.$invoke("hello", new String[]{String.class.getName()}, new Object[]{"Tom"});
        System.out.println("================ " + hello);
    }
}


