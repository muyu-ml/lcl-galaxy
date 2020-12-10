package com.lcl.galaxy.spring;

import com.lcl.galaxy.spring.transaction.domain.OrderInfoDo;
import com.lcl.galaxy.spring.transaction.domain.UserDo;
import com.lcl.galaxy.spring.transaction.service.UserService3;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.math.BigDecimal;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-transaction-xml-auto2-config.xml")
@EnableTransactionManagement
public class LclSpringAutoTransactionTest2 {

    @Autowired
    private UserService3 userService3;

    @Test
    public void test(){
        UserDo userDo = UserDo.builder().address("beijing").username("lcl111").sex("男").build();
        OrderInfoDo orderInfoDo = OrderInfoDo.builder().orderId("1111").name("lcl").payMoney(new BigDecimal(String.valueOf("1.01"))).build();
        log.info("测试启动");
        userService3.saveInfo(userDo, orderInfoDo);
        log.info("测试完成");
    }
}
