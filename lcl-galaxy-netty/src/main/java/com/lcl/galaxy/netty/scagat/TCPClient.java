package com.lcl.galaxy.netty.scagat;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 客户端需求 : 本节演示代码的重心在服务器端 ,
 * 服务器端演示 分散 聚合 的具体操作 ,
 * 客户端只是演示 网络套接字 流程 ,
 * 这里客户端使用 BIO , 使用 TCP 协议进行简单的数据发送 ;
 */
public class TCPClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket();
            InetSocketAddress inetSocketAddress =
                    new InetSocketAddress(
                            Inet4Address.getLocalHost(),   //本机IP地址
                            8888                      //端口号
                    );
            System.out.println("客户端开始连接 ...");
            //此处会阻塞等待连接成功
            socket.connect(inetSocketAddress);
            System.out.println("客户端连接成功");
            //连接成功后, 开始执行后续操作
            socket.getOutputStream().write("Hello World".getBytes());
            System.out.println("客户端写出 Hello World 成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
