package com.lcl.galaxy.spring;


import com.lcl.galaxy.spring.frame.apis.UserApis;
import com.lcl.galaxy.spring.frame.apis.UserApisV2;
import com.lcl.galaxy.spring.frame.dao.UserDao;
import com.lcl.galaxy.spring.frame.dao.UserDaoImpl;
import com.lcl.galaxy.spring.frame.service.UserService;
import com.lcl.galaxy.spring.frame.service.UserServiceImpl;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

public class FrameTest {

    @Test
    public void writeFrameV1() throws Exception {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://******:3306/ins_buying_0");
        dataSource.setUsername("********");
        dataSource.setPassword("********");
        UserDao userDao = new UserDaoImpl();
        ((UserDaoImpl) userDao).setDataSource(dataSource);
        UserService userService = new UserServiceImpl();
        ((UserServiceImpl) userService).setUserDao(userDao);
        UserApis userApis = new UserApis(userService);
        userApis.findUserById("2");
    }


    @Test
    public void writeFrameV2() throws Exception {
        UserApisV2 userApisV2 = new UserApisV2();
        userApisV2.init();
        userApisV2.findUserById("2");
    }
}
