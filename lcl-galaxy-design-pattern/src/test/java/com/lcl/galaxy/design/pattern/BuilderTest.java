package com.lcl.galaxy.design.pattern;

import com.alibaba.fastjson.JSON;
import com.lcl.galaxy.design.pattern.builder.Animal;
import com.lcl.galaxy.design.pattern.builder.AnimalBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class BuilderTest {

    @Test
    public void builderTest(){
        Animal animal = AnimalBuilder.builder().eye("blue").hair("yellow").build();
        log.info("=========== {} =========== ", JSON.toJSONString(animal));
    }

}
