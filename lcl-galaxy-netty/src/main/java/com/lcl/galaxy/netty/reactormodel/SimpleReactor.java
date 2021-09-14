package com.lcl.galaxy.netty.reactormodel;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 这是最基本的单Reactor单线程模型。
 * 其中SimpleReactor线程，负责多路分离套接字，
 * 有新连接到来触发connect事件之后，交由Acceptor进行处理，
 * 有IO读写事件之后交给SimpleHandler处理。
 * Acceptor主要任务就是构建handler，在获取到和client相关的SocketChannel之后 ，
 * 绑定到相应的hanlder上，对应的SocketChannel有读写事件之后，
 * 基于racotor 分发,hanlder就可以处理了（所有的IO事件都绑定到selector上，有Reactor分发）。
 * 该模型 适用于处理器链中业务处理组件能快速完成的场景。
 * 不过，这种单线程模型不能充分利用多核资源，所以实际使用的不多。
 *
 */
public class SimpleReactor implements Runnable {
    final Selector selector;
    final ServerSocketChannel serverSocket;

    SimpleReactor(int port) throws IOException {
        //Reactor初始化
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(port));
        //非阻塞
        serverSocket.configureBlocking(false);

        //分步处理,第一步,接收accept事件
        SelectionKey sk =  serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        //attach callback object, Acceptor
        sk.attach(new Acceptor());
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                selector.select();
                Set selected = selector.selectedKeys();
                Iterator it = selected.iterator();
                while (it.hasNext()) {
                    //Reactor负责dispatch收到的事件
                    System.out.println("reactorThread:"+Thread.currentThread().getName());
                    dispatch((SelectionKey) (it.next()));
                }
                selected.clear();
            }
        } catch (IOException ex)
        { /* ... */ }
    }

    void dispatch(SelectionKey k) {
        Runnable r = (Runnable) (k.attachment());
        //调用之前注册的callback对象
        if (r != null) {
            r.run();
        }
    }

    // inner class
    class Acceptor implements Runnable {
        public void run()
        {
            try
            {
                SocketChannel channel = serverSocket.accept();
                if (channel != null)
                    new SimpleHandler(selector, channel);
            } catch (IOException ex)
            { /* ... */ }
        }
    }

    public static void main(String[] args) throws IOException {
        SimpleReactor simpleReactor = new SimpleReactor(7002);
        new Thread(simpleReactor).start();
    }
}
