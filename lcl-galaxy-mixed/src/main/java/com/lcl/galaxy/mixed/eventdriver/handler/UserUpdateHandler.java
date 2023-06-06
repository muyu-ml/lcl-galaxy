package com.lcl.galaxy.mixed.eventdriver.handler;

import com.lcl.galaxy.mixed.eventdriver.event.UserCreateEvent;
import com.lcl.galaxy.mixed.eventdriver.event.UserUpdateEvent;
import com.lcl.galaxy.mixed.eventdriver.framework.Handler;

public class UserUpdateHandler implements Handler<UserUpdateEvent> {

    @Override
    public String onEvent(UserUpdateEvent event) {
        return String.format("User '%s' has been updated", event.getUser().getUserName());
    }
}
