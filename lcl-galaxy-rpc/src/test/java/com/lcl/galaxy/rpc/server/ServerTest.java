package com.lcl.galaxy.rpc.server;

import com.lcl.galaxy.rpc.service.impl.UserserviceImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ServerTest {

    public  static  void main(String[] args) {

        Map<String, Object> servicePool = new HashMap<String, Object>();
        servicePool.put("com.lcl.galaxy.rpc.service.UserService", new UserserviceImpl());

        RpcServer server = new RpcServer(servicePool, 4, 9001);

        try{
            server.service();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
