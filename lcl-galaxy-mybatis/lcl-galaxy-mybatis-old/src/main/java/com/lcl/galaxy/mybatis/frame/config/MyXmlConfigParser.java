package com.lcl.galaxy.mybatis.frame.config;

import com.lcl.galaxy.mybatis.frame.util.DocumentUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class MyXmlConfigParser {
    private MyConfiguration myConfiguration;


    public MyXmlConfigParser(){
        myConfiguration = new MyConfiguration();
    }

    public MyConfiguration parse(Element rootElement) {
        parseEnvironments(rootElement.element("environments"));
        parseMappers(rootElement.element("mappers"));
        return myConfiguration;
    }

    private void parseMappers(Element mappers) {
        List<Element> elementList = mappers.elements("mapper");
        for (Element mapperElement: elementList)  {
            String resource = mapperElement.attributeValue("resource");
            InputStream inputStream = MyResources.getResourceAsStream(resource);
            Document mapperDocument = DocumentUtils.readInputStream(inputStream);
            MyXmlMapperParse myXmlMapperParse = new MyXmlMapperParse(myConfiguration);
            myXmlMapperParse.parse(mapperDocument);
        }
    }

    private void parseEnvironments(Element environments) {
        Properties properties = null;
        String aDefault = environments.attributeValue("default");
        List<Element> elements = environments.elements("environment");
        for (Element element: elements) {
            String id = element.attributeValue("id");
            if(id.equals(aDefault)){
                parseEnvironment(element);
                break;
            }
        }
    }

    private void parseEnvironment(Element element) {
        Properties properties = null;
        BasicDataSource dataSource = new BasicDataSource();
        Element dataSourceElement = element.element("dataSource");
        String type = dataSourceElement.attributeValue("type");
        type = type == null || type.equals("") ? "POOLED":type;
        if (type.equals("POOLED")){
            properties = parseProperties(dataSourceElement);
            dataSource.setDriverClassName(properties.getProperty("driver"));
            dataSource.setUrl(properties.getProperty("url"));
            dataSource.setUsername(properties.getProperty("username"));
            dataSource.setPassword(properties.getProperty("password"));
        }
        myConfiguration.setDataSource(dataSource);
    }

    private Properties parseProperties(Element dataSourceElement) {
        Properties properties = new Properties();
        List<Element> elements = dataSourceElement.elements("property");
        for (Element property: elements) {
            properties.put(property.attributeValue("name"),property.attributeValue("value"));
        }
        return properties;
    }
}
