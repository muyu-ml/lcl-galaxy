package com.lcl.galaxy.mixed;

import com.lcl.galaxy.mixed.eventdriver.event.UserCreateEvent;
import com.lcl.galaxy.mixed.eventdriver.event.UserUpdateEvent;
import com.lcl.galaxy.mixed.eventdriver.framework.EventDispatcher;
import com.lcl.galaxy.mixed.eventdriver.handler.UserCreateHandler;
import com.lcl.galaxy.mixed.eventdriver.handler.UserUpdateHandler;
import com.lcl.galaxy.mixed.eventdriver.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LclGalaxyMixedApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext app = SpringApplication.run(LclGalaxyMixedApplication.class, args);
        EventDispatcher eventDispatcher = new EventDispatcher();
        eventDispatcher.registerHandler(UserCreateEvent.class, new UserCreateHandler());
        eventDispatcher.registerHandler(UserUpdateEvent.class, new UserUpdateHandler());
        User user = new User("lcl");
        String result = eventDispatcher.dispatch(new UserCreateEvent(user));
        System.out.println(result);
        result = eventDispatcher.dispatch(new UserUpdateEvent(user));
        System.out.println(result);
        SpringApplication.exit(app);
    }

}
