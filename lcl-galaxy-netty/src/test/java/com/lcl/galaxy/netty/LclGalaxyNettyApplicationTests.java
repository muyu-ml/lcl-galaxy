package com.lcl.galaxy.netty;

import com.lcl.galaxy.netty.zero.MappedByteBufferTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LclGalaxyNettyApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void writeToFileByMappedByteBuffer(){
        MappedByteBufferTest test = new MappedByteBufferTest();
        test.writeToFileByMappedByteBuffer();
    }

    @Test
    void readFromFileByMappedByteBuffer(){
        MappedByteBufferTest test = new MappedByteBufferTest();
        test.readFromFileByMappedByteBuffer();
    }
}
