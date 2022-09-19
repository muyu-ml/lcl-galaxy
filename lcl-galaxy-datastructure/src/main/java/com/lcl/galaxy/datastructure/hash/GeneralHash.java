package com.lcl.galaxy.datastructure.hash;

import java.util.SortedMap;
import java.util.TreeMap;

public class GeneralHash {

    /**
     * hash
     * @param args
     */
    public static void main1(String[] args) {
        //定义客户端ip
        String[] clients = new String[]{"192.168.33.1","192.168.33.106","192.168.33.205","192.168.33.10","192.168.33.5"};

        //定义服务器数量
        int serverCount = 5;

        for(String client:clients){
            //对客户端的ip求hash值
            int clientCode = Math.abs(client.hashCode());

            //将ip对应的hash值和服务器数量进行一个取模的绝对值
            int code = clientCode%serverCount;

            System.out.println("客户端ip为"+client+"的请求服务器为"+code+"号服务器");
        }
    }


    /**
     * 一致性hash
     * @param args
     */
    public static void main2(String[] args) {
        //step1：定义server服务器的ip值映射到哈希环上
        String[] tomcatServers = new String[]{"123.111.0.0","123.101.3.1","111.20.35.2","123.98.26.3"};

        //使用sortedMap模拟哈希环
        SortedMap<Integer,String> serverMap = new TreeMap<>();

        for(String tomcatServer:tomcatServers){
            //计算每个服务器id的哈希值
            int serverHash = Math.abs(tomcatServer.hashCode());

            //将服务端哈希值以及其ip地址的对应关系存储到sortedMap中
            serverMap.put(serverHash,tomcatServer);
        }

        //step2:针对客户端的ip计算出对应的哈希值
        String[] clientServers = new String[]{"10.78.12.3","113.25.63.1","126.12.3.8"};

        for(String clientServer:clientServers){
            int clientHash = Math.abs(clientServer.hashCode());

            //step3:看客户端的ip能够被哪个服务器所处理
            //顺时针获取比这个客户端ip的hash值大的服务
            SortedMap<Integer, String> integerStringSortedMap = serverMap.tailMap(clientHash);
            if(integerStringSortedMap.isEmpty()){
                //如果查询出来为空的，那么取哈希环上的第一个值
                Integer integer = serverMap.firstKey();

                System.out.println("客户端:"+clientServer+"路由到的tomcat服务器ip为"+serverMap.get(integer));
            }else{
                Integer integer = integerStringSortedMap.firstKey();

                System.out.println("客户端:"+clientServer+"路由到的tomcat服务器ip为"+serverMap.get(integer));
            }
        }
    }


    public static void main4(String[] args) {
        //step1：定义server服务器的ip值映射到哈希环上
        String[] tomcatServers = new String[]{"123.111.0.0","123.101.3.1","111.20.35.2","123.98.26.3"};

        //使用sortedMap模拟哈希环
        SortedMap<Integer,String> serverMap = new TreeMap<Integer, String>();

        //定义虚拟节点的数量
        int virtualCount = 3;

        for(String tomcatServer:tomcatServers){
            //计算每个服务器id的哈希值
            int serverHash = Math.abs(tomcatServer.hashCode());

            //将服务端哈希值以及其ip地址的对应关系存储到sortedMap中
            serverMap.put(serverHash,tomcatServer);

            //配置虚拟节点
            for(int i = 0;i<virtualCount;i++){
                //计算虚拟节点的哈希值
                int virtualHash = Math.abs((tomcatServer+"#"+i).hashCode());

                //将虚拟节点哈希值与服务器关系存储到
                serverMap.put(virtualHash,"由虚拟节点"+i+"映射过来的请求:"+tomcatServer);
            }
        }

        //step2:针对客户端的ip计算出对应的哈希值
        String[] clientServers = new String[]{"10.78.12.3","113.25.63.1","126.12.3.8"};

        for(String clientServer:clientServers){
            int clientHash = Math.abs(clientServer.hashCode());

            //step3:看客户端的ip能够被哪个服务器所处理
            //顺时针获取比这个客户端ip的hash值大的服务
            SortedMap<Integer, String> integerStringSortedMap = serverMap.tailMap(clientHash);
            if(integerStringSortedMap.isEmpty()){
                //如果查询出来为空的，那么取哈希环上的第一个值
                Integer integer = serverMap.firstKey();

                System.out.println("客户端:"+clientServer+"路由到的tomcat服务器ip为"+serverMap.get(integer));
            }else{
                Integer integer = integerStringSortedMap.firstKey();

                System.out.println("客户端:"+clientServer+"路由到的tomcat服务器ip为"+serverMap.get(integer));
            }
        }
    }

    public static void main(String[] args) {
        GeneralHash generalHash = new GeneralHash();
        int i = generalHash.test1();
        System.out.println("=========" + i);
    }

    public int test1(){
        int i = 10;
        try {
            i=i+3;
            return i;
        }catch (Exception e){

        }finally {
            i = i + 1;
            return i;
        }
    }
}
