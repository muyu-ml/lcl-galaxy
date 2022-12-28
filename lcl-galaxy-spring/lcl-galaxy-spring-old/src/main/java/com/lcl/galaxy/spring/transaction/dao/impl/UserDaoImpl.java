package com.lcl.galaxy.spring.transaction.dao.impl;

import com.lcl.galaxy.spring.transaction.dao.UserDao;
import com.lcl.galaxy.spring.transaction.domain.UserDo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

@Slf4j
@Data
public class UserDaoImpl implements UserDao {

    @Autowired
    private DataSource dataSource;

    @Override
    public void insert(UserDo userDo) {
        log.info("插入user={}", userDo);
    }
}
