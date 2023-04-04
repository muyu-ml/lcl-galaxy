package com.lcl.galaxy.rpc.client;

import com.lcl.galaxy.rpc.protocol.Protocol;

public class ClientTest {

    public  static  void main(String[] args) {

        String serverAddress = "127.0.0.1";
        int serverPort = 9001;

        RpcClient client = new RpcClient(serverAddress, serverPort);
        Protocol protocol = buildProtocol("1");
        Object result = client.sendAndReceive(protocol);
        System.out.println(result);

        protocol = buildProtocol("2");
        result = client.sendAndReceive(protocol);
        System.out.println(result);

    }

    private  static Protocol buildProtocol(String userCode) {
        String interfaceName = "com.lcl.galaxy.rpc.service.UserService";
        Class[] paramsTypes = {String.class};
        Object[] parameters = {userCode};
        String methodName = "getUserNameByCode";

        Protocol transportMessage = new Protocol(interfaceName, methodName, paramsTypes, parameters);
        return transportMessage;
    }
}
