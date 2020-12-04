package com.lcl.galaxy.mybatis.frame.sqlsession;

import com.lcl.galaxy.mybatis.frame.config.MyConfiguration;
import com.lcl.galaxy.mybatis.frame.config.MyResources;
import com.lcl.galaxy.mybatis.frame.config.MyXmlConfigParser;
import com.lcl.galaxy.mybatis.frame.util.DocumentUtils;
import org.dom4j.Document;

import java.io.InputStream;
import java.net.URL;

public class MysessionFactorBuilder {
    public static MySqlSessionFactory build(String resource) {
        InputStream inputStream = MyResources.class.getClassLoader().getResourceAsStream(resource);
        return build(inputStream);
    }

    public static MySqlSessionFactory build(InputStream inputStream) {
        MyXmlConfigParser myXmlConfigParser = new MyXmlConfigParser();
        Document document = DocumentUtils.readInputStream(inputStream);
        MyConfiguration myConfiguration = myXmlConfigParser.parse(document.getRootElement());
        return new MyDefaultSqlSessionFactory(myConfiguration);
    }


}
