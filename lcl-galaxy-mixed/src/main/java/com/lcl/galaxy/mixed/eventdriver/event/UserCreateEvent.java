package com.lcl.galaxy.mixed.eventdriver.event;

import com.lcl.galaxy.mixed.eventdriver.model.User;

public class UserCreateEvent extends AbstractEvent{
    private User user;

    public UserCreateEvent(User user){
        this.user = user;
    }

    public User getUser(){
        return this.user;
    }
}
