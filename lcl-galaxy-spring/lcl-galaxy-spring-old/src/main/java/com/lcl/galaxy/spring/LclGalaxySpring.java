package com.lcl.galaxy.spring;

import com.lcl.galaxy.spring.aop.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class LclGalaxySpring {

    public static void main(String[] args) {
        //ApplicationContext factory1=new ClassPathXmlApplicationContext("classpath:spring-config1.xml");
        //ApplicationContext factory2=new ClassPathXmlApplicationContext("classpath:spring-config2.xml");

        //ApplicationContext applicationContext1 = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        //ApplicationContext applicationContext2 = new AnnotationConfigApplicationContext(JdbcConfig.class);


        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-config4.xml");
        UserService userService = (UserService) context.getBean("userServiceImpl");
        /*userService.insert();
        userService.insert("1");
        userService.insert("","");
        userService.userInsert();*/
        String name = userService.around("lcl");

        log.info("=============={}", name);
    }

}
