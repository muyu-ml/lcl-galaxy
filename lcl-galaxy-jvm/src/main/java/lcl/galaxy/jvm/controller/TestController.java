package lcl.galaxy.jvm.controller;

import lcl.galaxy.jvm.dto.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("gc")
public class TestController {

    @GetMapping("/fast")
    public String fastTest(){
        List<User> list = new ArrayList<>();
        for (int i=0; i<100; i++){
            User u = new User();
            list.add(u);
        }
        return "fast ok";
    }

    @GetMapping("/slow")
    public String slowTest(){
        List<User> list = new ArrayList<>();
        for (int i=0; i<100; i++){
            User u = new User();
            list.add(u);
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "slow ok";
    }
}
