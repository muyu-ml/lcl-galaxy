package com.lcl.galaxy.lcl.galaxy.mysql;

import com.lcl.galaxy.lcl.galaxy.mysql.dao.UserMapper;
import com.lcl.galaxy.lcl.galaxy.mysql.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class SampleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {


        for(int i=0; i< 10; i++){
            User user = User.builder().id(1L).name("Jone" + i).age(18).email("").build();
            userMapper.updateById(user);
        }
        for(int i=0; i< 10; i++){
            List<User> userList = userMapper.selectList(null);
            userList.forEach(System.out::println);
        }
    }

}
