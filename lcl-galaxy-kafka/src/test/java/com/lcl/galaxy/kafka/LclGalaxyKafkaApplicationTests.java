package com.lcl.galaxy.kafka;

import com.lcl.galaxy.kafka.nativeapi.NativeService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = LclGalaxyKafkaApplication.class)
class LclGalaxyKafkaApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void demoTest()  throws Exception{
        NativeService nativeService = new NativeService();
        nativeService.sendMsg();
    }

}
