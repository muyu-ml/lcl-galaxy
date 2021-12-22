package com.abc.consumer;

import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerRun1 {
    public static void main(String[] args) {
        ApplicationContext ac =
                new ClassPathXmlApplicationContext("spring-consumer.xml");
        GenericService genericService = (GenericService) ac.getBean("gene");
        Object geneHello = genericService.$invoke("geneHello", new String[]{String.class.getName()}, new Object[]{"lcl"});
        System.out.println(geneHello);
    }
}
