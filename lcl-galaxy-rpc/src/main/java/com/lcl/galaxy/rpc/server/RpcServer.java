package com.lcl.galaxy.rpc.server;

import com.lcl.galaxy.rpc.protocol.Protocol;
import lombok.Data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Data
public class RpcServer {
    // 自定义线程池相关内容
    private int threadSize = 10;
    private ExecutorService threadPool;
    //自定义缓存
    private Map<String, Object> servicePool;
    // 自定义端口
    private int port = 9000;

    public RpcServer(){
        super();
        synchronized (this){
            threadPool = Executors.newFixedThreadPool(threadSize);
        }
    }

    public RpcServer(Map<String, Object> servicePool, int threadSize, int port) {
        super();
        this.port = port;
        this.servicePool = servicePool;
        synchronized (this){
            threadPool = Executors.newFixedThreadPool(threadSize);
        }
    }

    public void service() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        // 执行请求
        while (true){
            Socket receiveSocket = serverSocket.accept();
            final Socket socket = receiveSocket;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        // 处理请求
                        process(socket);
                        socket.close();
                    } catch (IOException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | InstantiationException e) {
                        e.printStackTrace();
                    }
                }


            });
        }
    }

    // 处理请求
    private void process(Socket socket) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Protocol protocolMessage = (Protocol) objectInputStream.readObject();
        Object result = call(protocolMessage);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(result);
        objectOutputStream.close();
    }


    //3.执行方法调用：RpcInvoker
    private Object call(Protocol protocol) throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InstantiationException, InvocationTargetException {

        if(servicePool == null) {
            synchronized (this) {
                servicePool = new HashMap<String, Object>();
            }
        }

        //通过接口名称构建实现类
        String interfaceName = protocol.getInterfaceName();
        Class<?> serviceClass = Class.forName(interfaceName);

        Object service = servicePool.get(interfaceName);
        //判断servicePool对象是否存在，如果不存在，就创建新对象并放入池中
        if(service == null) {
            synchronized (this) {
                service = serviceClass.newInstance();
                servicePool.put(interfaceName, service);
            }
        }

        //通过实现类来构建方法
        Method method = serviceClass.getMethod(protocol.getMethodName(), protocol.getParamsTypes());

        //通过反射来实现方法的执行
        Object result = method.invoke(service, protocol.getParameters());
        return result;
    }


}
