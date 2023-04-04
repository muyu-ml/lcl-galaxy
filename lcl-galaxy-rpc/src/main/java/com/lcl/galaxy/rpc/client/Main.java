package com.lcl.galaxy.rpc.client;

import com.lcl.galaxy.rpc.service.UserService;

public class Main {
    public static void main(String[] args) {
        NettyRpcClient1 nettyRpcClient1 = new NettyRpcClient1();
        nettyRpcClient1.start("127.0.0.1", 8111);

        NettyRpcClient2 nettyRpcClient2 = new NettyRpcClient2();
        nettyRpcClient2.start("127.0.0.1", 8111);

        NettyRpcClient3 nettyRpcClient3 = new NettyRpcClient3();
        nettyRpcClient3.start("127.0.0.1", 8111);
        UserService userService = nettyRpcClient3.proxy(UserService.class);
        String userNameByCode = userService.getUserNameByCode("2");
        System.out.println(userNameByCode);

    }
}
