package com.lcl.mybatisnative.sqlsession;

import com.lcl.mybatisnative.config.MyConfiguration;
import com.lcl.mybatisnative.config.MyResources;
import com.lcl.mybatisnative.config.MyXmlConfigParser;
import com.lcl.mybatisnative.util.DocumentUtils;
import org.dom4j.Document;

import java.io.InputStream;

public class MySqlSessionFactorBuilder {
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
