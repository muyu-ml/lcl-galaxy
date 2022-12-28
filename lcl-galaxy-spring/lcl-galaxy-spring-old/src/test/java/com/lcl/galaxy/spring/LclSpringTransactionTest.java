package com.lcl.galaxy.spring;

import com.lcl.galaxy.spring.transaction.domain.OrderInfoDo;
import com.lcl.galaxy.spring.transaction.domain.UserDo;
import com.lcl.galaxy.spring.transaction.service.UserService;
import com.lcl.galaxy.spring.transaction.service.UserService2;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-transaction-xml-config.xml")
public class LclSpringTransactionTest {



    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-transaction-config.xml");
        UserService userService = (UserService) context.getBean("userService");
        UserDo userDo = UserDo.builder().address("beijing").username("lcl111").sex("男").build();
        OrderInfoDo orderInfoDo = OrderInfoDo.builder().orderId("1111").name("lcl").payMoney(new BigDecimal(String.valueOf("1.01"))).build();
        log.info("测试启动");
        userService.transactionTest(userDo, orderInfoDo);
        log.info("测试完成");
    }


    @Autowired
    private UserService2 userService2;

    @Test
    public void test2(){
        UserDo userDo = UserDo.builder().address("beijing").username("lcl111").sex("男").build();
        OrderInfoDo orderInfoDo = OrderInfoDo.builder().orderId("1111").name("lcl").payMoney(new BigDecimal(String.valueOf("1.01"))).build();
        log.info("测试启动");
        userService2.saveInfo(userDo, orderInfoDo);
        log.info("测试完成");
    }
}
