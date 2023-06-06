package com.lcl.galaxy.mixed.eventdriver.framework;

import java.util.HashMap;
import java.util.Map;

public class EventDispatcher {
    private Map<Class<? extends Event>, Handler<? extends Event>> handlers;
    public EventDispatcher(){
        handlers = new HashMap<>();
    }

    // 注册
    public void registerHandler(Class<? extends Event> eventType, Handler<? extends Event> handler){
        handlers.put(eventType, handler);
    }

    // 分派事件
    public <E extends Event> String dispatch(E event){
        Handler<E> handler = (Handler<E>)handlers.get(event.getClass());
        if(handler != null){
            String result = handler.onEvent(event);
            return result;
        }
        return "error";
    }
}
