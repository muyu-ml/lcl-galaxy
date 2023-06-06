package com.lcl.galaxy.mixed.eventdriver.framework;

public interface Event {
    Class<? extends Event> getType();
}
