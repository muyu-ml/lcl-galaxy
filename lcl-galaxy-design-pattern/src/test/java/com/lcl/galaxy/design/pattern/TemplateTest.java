package com.lcl.galaxy.design.pattern;

import com.alibaba.fastjson.JSON;
import com.lcl.galaxy.design.pattern.builder.Animal;
import com.lcl.galaxy.design.pattern.builder.AnimalBuilder;
import com.lcl.galaxy.design.pattern.template.GoHomeTemplate;
import com.lcl.galaxy.design.pattern.template.ShipGoHome;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class TemplateTest {

    @Test
    public void builderTest(){
        GoHomeTemplate shipGoHome = new ShipGoHome();
        shipGoHome.goHomeMian();
    }

}
