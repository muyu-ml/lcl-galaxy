package com.lcl.galaxy.netty.nio.zerocopy;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class DirectByteBufferExample {
    public static void main (String [] args)
            throws Exception {

        long startTime = new Date().getTime();

        Path path = Paths.get("/Users/conglongli/Desktop/JVM.zip");
        FileChannel fileChannel = FileChannel.open(path);

        ByteBuffer buffer = ByteBuffer.allocateDirect(1024*10); // 2095
//        ByteBuffer buffer = ByteBuffer.allocate(1024*10); //传统IO 3027



        System.out.println("Is a direct buffer: " + buffer.isDirect());
        System.out.println("Buffer has a backing array: " + buffer.hasArray());
        System.out.println("Reading file... ");

        int noOfBytesRead = fileChannel.read(buffer);

        for (int i = 0; i < 25; i++) {

            while (noOfBytesRead != -1) {

                buffer.clear();
                noOfBytesRead = fileChannel.read(buffer);
            }

            buffer.clear();
            fileChannel.position(0);
            noOfBytesRead = fileChannel.read(buffer);
        }

        fileChannel.close();

        long endTime = new Date().getTime();

        System.out.println("Time taken (millis): " + (endTime - startTime));
    }
}
