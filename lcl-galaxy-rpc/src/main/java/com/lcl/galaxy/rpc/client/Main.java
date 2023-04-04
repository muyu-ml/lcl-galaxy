package com.lcl.galaxy.rpc.client;

import com.lcl.galaxy.rpc.service.UserService;

public class Main {
    public static void main(String[] args) {
        NettyRpcClient1 nettyRpcClient1 = new NettyRpcClient1();
        nettyRpcClient1.start("127.0.0.1", 8111);

        NettyRpcClient2 nettyRpcClient2 = new NettyRpcClient2();
        nettyRpcClient2.start("127.0.0.1", 8111);



    }
}
