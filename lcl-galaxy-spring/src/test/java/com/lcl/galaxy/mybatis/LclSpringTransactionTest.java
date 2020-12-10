package com.lcl.galaxy.mybatis;

import com.lcl.galaxy.spring.transaction.domain.OrderInfoDo;
import com.lcl.galaxy.spring.transaction.domain.UserDo;
import com.lcl.galaxy.spring.transaction.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;

@Slf4j
public class LclSpringTransactionTest {

    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-transaction-config.xml");
        UserService userService = (UserService) context.getBean("userServiceImpl");
        UserDo userDo = UserDo.builder().address("beijing").username("lcl111").sex("男").build();
        OrderInfoDo orderInfoDo = OrderInfoDo.builder().orderId("1111").name("lcl").payMoney(new BigDecimal(String.valueOf("1.01"))).build();
        userService.transactionTest(userDo, orderInfoDo);
    }
}
