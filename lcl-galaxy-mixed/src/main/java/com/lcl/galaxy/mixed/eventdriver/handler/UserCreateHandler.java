package com.lcl.galaxy.mixed.eventdriver.handler;

import com.lcl.galaxy.mixed.eventdriver.event.UserCreateEvent;
import com.lcl.galaxy.mixed.eventdriver.framework.Event;
import com.lcl.galaxy.mixed.eventdriver.framework.Handler;

public class UserCreateHandler implements Handler<UserCreateEvent> {

    @Override
    public String onEvent(UserCreateEvent event) {
        return String.format("User '%s' has been created", event.getUser().getUserName());
    }
}


