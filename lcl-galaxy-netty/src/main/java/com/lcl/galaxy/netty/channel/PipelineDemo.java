package com.lcl.galaxy.netty.channel;

public class PipelineDemo {
    //初始化链的头部
    public HandlerContext head = new HandlerContext(new AbstractHandler() {
        @Override
        void doHandler(HandlerContext context, Object arg) {
            context.runNext(arg);
        }
    });

    //开始执行
    public void request(Object arg) {
        this.head.handler(arg);
    }

    //添加节点到尾部
    public void addLast(AbstractHandler handler) {
        HandlerContext context = head;
        while (context.next != null) {
            context = context.next;
        }
        context.next = new HandlerContext(handler);
    }


    public static void main(String[] args) {
        PipelineDemo pipelineChainDemo = new PipelineDemo();
        pipelineChainDemo.addLast(new Handler2());
        pipelineChainDemo.addLast(new Handler1());
        pipelineChainDemo.addLast(new Handler1());
        pipelineChainDemo.addLast(new Handler2());

        // 发起请求
        pipelineChainDemo.request("火车呜呜呜~~");
    }

}

//处理器的信息，维护处理器
class HandlerContext {
    //下一个节点
    HandlerContext next;
    AbstractHandler handler;

    public HandlerContext(AbstractHandler handler) {
        this.handler = handler;
    }

    void handler(Object arg) {
        this.handler.doHandler(this, arg);
    }

    //执行下一个
    void runNext(Object arg) {
        if (this.next != null) {
            this.next.handler(arg);
        }
    }
}

//处理器抽象类
abstract class AbstractHandler {
    abstract void doHandler(HandlerContext context, Object arg);
}

//处理器的具体实现类
class Handler1 extends AbstractHandler {

    @Override
    void doHandler(HandlerContext context, Object arg) {
        arg = arg.toString() + "Handler1的小尾巴～～";
        System.out.println("Handler1的实例正在处理：" + arg);
        //执行下一个
        context.runNext(arg);
    }
}

//处理器的具体实现类
class Handler2 extends AbstractHandler {

    @Override
    void doHandler(HandlerContext context, Object arg) {
        arg = arg.toString() + "Handler2的小尾巴～～";
        System.out.println("Handler2的实例正在处理：" + arg);
        //执行下一个
        context.runNext(arg);
    }
}



