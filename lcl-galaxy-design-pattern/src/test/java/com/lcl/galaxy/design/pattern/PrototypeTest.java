package com.lcl.galaxy.design.pattern;

import com.alibaba.fastjson.JSON;
import com.lcl.galaxy.design.pattern.builder.Animal;
import com.lcl.galaxy.design.pattern.builder.AnimalBuilder;
import com.lcl.galaxy.design.pattern.prototype.Order;
import com.lcl.galaxy.design.pattern.prototype.Prototype;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@Slf4j
@SpringBootTest
public class PrototypeTest {

    @Test
    public void prototypeShallowCloneTest() throws CloneNotSupportedException {
        Prototype prototype = getPrototype();
        Prototype copyPrototype = prototype.shallowClone();
        print(prototype, copyPrototype);
    }

    @Test
    public void prototypeDeepCloneTest() throws IOException, ClassNotFoundException {
        Prototype prototype = getPrototype();
        Prototype copyPrototype = prototype.deepClone();
        print(prototype, copyPrototype);
    }


    private Prototype getPrototype(){
        Order order = new Order();
        order.setOrderId("1234");
        Prototype prototype = new Prototype();
        prototype.setOrder(order);
        prototype.setName("lcl");
        return prototype;
    }

    private void print(Prototype prototype, Prototype copyPrototype){
        log.info("prototype=================={}=================", prototype);
        log.info("copyPrototype=================={}=================", copyPrototype);
        log.info("prototype=================={}=================", JSON.toJSONString(prototype));
        log.info("copyPrototype=================={}=================", JSON.toJSONString(copyPrototype));
        log.info("prototype.getOrder()=================={}=================", prototype.getOrder());
        log.info("copyPrototype.getOrder()=================={}=================", copyPrototype.getOrder());
        log.info("prototype.equals(copyPrototype)=================={}=================", prototype.equals(copyPrototype));
        log.info("prototype.getName().equals(copyPrototype.getName())=================={}=================", prototype.getName().equals(copyPrototype.getName()));
        log.info("prototype.getOrder().equals(copyPrototype.getOrder())=================={}=================", prototype.getOrder().equals(copyPrototype.getOrder()));
        log.info("prototype.getOrder().getOrderId().equals(copyPrototype.getOrder().getOrderId())=================={}=================", prototype.getOrder().getOrderId().equals(copyPrototype.getOrder().getOrderId()));
    }

}
