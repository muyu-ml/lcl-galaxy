package com.lcl.galaxy.mixed.eventdriver.event;

import com.lcl.galaxy.mixed.eventdriver.framework.Event;

public abstract class AbstractEvent implements Event {
    @Override
    public Class<? extends Event> getType() {
        return getClass();
    }
}
