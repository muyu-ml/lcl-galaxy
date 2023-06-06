package com.lcl.galaxy.mixed.eventdriver.event;

import com.lcl.galaxy.mixed.eventdriver.model.User;

public class UserUpdateEvent extends AbstractEvent{
    private User user;

    public UserUpdateEvent(User user){
        this.user = user;
    }

    public User getUser(){
        return this.user;
    }
}
