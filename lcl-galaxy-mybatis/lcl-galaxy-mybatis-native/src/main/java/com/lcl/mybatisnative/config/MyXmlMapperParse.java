package com.lcl.mybatisnative.config;

import org.dom4j.Document;
import org.dom4j.Element;

import java.util.List;

public class MyXmlMapperParse {

    private MyConfiguration myConfiguration;

    public MyXmlMapperParse(MyConfiguration myConfiguration){
        this.myConfiguration = myConfiguration;
    }

    public void parse(Document mapperDocument) {
        Element rootElement = mapperDocument.getRootElement();
        String namespace = rootElement.attributeValue("namespace");
        List<Element> elements = rootElement.elements("select");
        for (Element element: elements) {
            MyXmlStatementParser myXmlStatementParser = new MyXmlStatementParser(myConfiguration);
            myXmlStatementParser.parse(element, namespace);
        }

    }
}
