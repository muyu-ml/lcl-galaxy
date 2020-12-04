package com.lcl.galaxy.mybatis;

import com.lcl.galaxy.mybatis.common.dao.FrameUserDao;
import com.lcl.galaxy.mybatis.common.dao.impl.FrameUserDaoImpl;
import com.lcl.galaxy.mybatis.frame.config.MyConfiguration;
import com.lcl.galaxy.mybatis.frame.config.MyXmlConfigParser;
import com.lcl.galaxy.mybatis.frame.sqlsession.MySqlSessionFactory;
import com.lcl.galaxy.mybatis.frame.sqlsession.MysessionFactorBuilder;
import com.lcl.galaxy.mybatis.frame.util.DocumentUtils;
import com.lcl.galaxy.mybatis.frame.config.MyResources;
import com.lcl.galaxy.mybatis.simple.common.domain.UserDo;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.junit.Test;

import java.io.InputStream;

@Slf4j
public class FrameTest {


    @Test
    public void testFrame() throws Exception {
        String resource = "FrameSqlMapConfig.xml";
        InputStream inputStream = MyResources.getResourceAsStream(resource);
        Document document = DocumentUtils.readInputStream(inputStream);
        MyXmlConfigParser xmlConfigParser = new MyXmlConfigParser();
        MyConfiguration configuration = xmlConfigParser.parse(document.getRootElement());
        log.info("configuration=====【{}】", configuration);
    }

    @Test
    public void testFrameQuery() throws Exception {
        UserDo userDo = new UserDo();
        userDo.setId(1);
        userDo.setUsername("lcl");
        String resource = "FrameSqlMapConfig.xml";
        MySqlSessionFactory mySqlSessionFactory = MysessionFactorBuilder.build(resource);
        FrameUserDao frameUserDao = new FrameUserDaoImpl(mySqlSessionFactory);
        UserDo user = frameUserDao.findUser(userDo);
        log.info("user======【{}】", user);
    }


}
