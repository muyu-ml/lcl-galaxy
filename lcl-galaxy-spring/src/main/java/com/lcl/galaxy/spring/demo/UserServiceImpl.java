package com.lcl.galaxy.spring.demo;

import com.lcl.galaxy.spring.dao.UserDao;
import com.lcl.galaxy.spring.domain.UserDo;
import com.lcl.galaxy.spring.service.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class UserServiceImpl implements UserService {

    private String id;
    private String name;

    private UserDao userDao;

    @Override
    public UserDo getUserById() {
        return UserDo.builder().id(this.id).name(this.name).build();
    }

    public UserServiceImpl(){
        this.id = "initId";
        this.name = "initName";
        log.info("无参构造");
    }

    public UserServiceImpl(String id, String name){
        this.id = id;
        this.name = name;
        log.info("有参构造：id={}，name={}", id, name);
    }

}
