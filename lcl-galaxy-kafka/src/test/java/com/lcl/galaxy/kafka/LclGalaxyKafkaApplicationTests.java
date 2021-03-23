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
    void sendTest()  throws Exception{
        NativeService nativeService = new NativeService();
        nativeService.sendMsg();
    }

    @Test
    void batchSendTest()  throws Exception{
        NativeService nativeService = new NativeService();
        nativeService.sendMsg1();
    }


    @Test
    void consumerTest()  throws Exception{
        NativeService nativeService = new NativeService();
        nativeService.doWork();
    }


    @Test
    void syncConsumerTest()  throws Exception{
        NativeService nativeService = new NativeService();
        nativeService.doSyncWork();
    }

    @Test
    void asyncConsumerTest()  throws Exception{
        NativeService nativeService = new NativeService();
        nativeService.doAsyncWork();
    }

}
