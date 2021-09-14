package com.lcl.galaxy.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class NettyByteBuf01 {
    public static void main2(String[] args) {

        //创建一个ByteBuf
        //1. 创建 对象，该对象包含一个数组arr , 是一个byte[10]
        //2. 在netty 的buffer中，不需要使用flip 进行反转
        //   底层维护了 readerindex 和 writerIndex
        //3. 通过 readerindex 和  writerIndex 和  capacity， 将buffer分成三个区域
        // 0---readerindex 已经读取的区域
        // readerindex---writerIndex ， 可读的区域
        // writerIndex -- capacity, 可写的区域
        ByteBuf buffer = Unpooled.buffer(10);

        for (int i = 0; i < 10; i++) {
            System.out.println("writerIndex=" + buffer.writerIndex());//10
            buffer.writeByte(i);

        }

        System.out.println("capacity=" + buffer.capacity());//10


        //读取第一种方式
        while (buffer.isReadable()){
            System.out.println(buffer.readByte()+",readerIndex="+buffer.readerIndex());

        }

        System.out.println("============================");
        //输出 读取第二种方式
        for(int i = 0; i<buffer.capacity(); i++) {
            System.out.println(buffer.getByte(i)+",readerIndex="+buffer.readerIndex());
        }

        buffer.discardReadBytes();
        System.out.println("readerIndex="+buffer.readerIndex()+",writeIndex="+buffer.writerIndex());


        System.out.println("done");
    }

    /**
     * 进行readByte和writeByte方法的调用时会改变readIndex和writeIndex的值，
     * 而调用set和get方法时不会改变readIndex和writeIndex的值。
     *测试案例中打印的writeIndex和readIndex均为1,并未在调用set和get方法后被改变。
     *
     * @param args
     */
    public static void main(String[] args) {
        ByteBuf heapBuf = Unpooled.buffer(5);
        heapBuf.writeByte(1);
        System.out.println("writeIndex : " + heapBuf.writerIndex());//writeIndex : 1
        heapBuf.readByte();
        System.out.println("readIndex : " + heapBuf.readerIndex());//readIndex : 1
        heapBuf.setByte(2, 2);
        System.out.println("writeIndex : " + heapBuf.writerIndex());//writeIndex : 1
        heapBuf.getByte(2);
        System.out.println("readIndex : " + heapBuf.readerIndex());//readIndex : 1
    }
}
