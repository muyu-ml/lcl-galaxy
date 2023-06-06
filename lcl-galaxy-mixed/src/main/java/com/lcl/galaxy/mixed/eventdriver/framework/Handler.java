package com.lcl.galaxy.mixed.eventdriver.framework;

public interface Handler<E extends Event> {
    String onEvent(E event);
}
