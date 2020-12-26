package com.lcl.galaxy.springmvc.frame.springframe.reader;

import com.lcl.galaxy.springmvc.frame.springframe.register.MyBeanDefinitionRegisty;
import com.lcl.galaxy.springmvc.frame.springframe.resource.MyResources;
import com.lcl.galaxy.springmvc.frame.springframe.utils.DocumentUtils;
import org.dom4j.Document;

import java.io.InputStream;

public class MyXmlBeanDefinitionReader {

    private MyBeanDefinitionRegisty registy;

    public MyXmlBeanDefinitionReader(MyBeanDefinitionRegisty registy) {
        this.registy = registy;
    }

    public void loadBeanDefinitions(MyResources resources) {
        InputStream inputStream = resources.getResourceAsStream();
        Document document = DocumentUtils.readInputStream(inputStream);
        MyXmlBeanDefinitionDocumentReader xmlBeanDefinitionDocumentReader = new MyXmlBeanDefinitionDocumentReader(registy);
        xmlBeanDefinitionDocumentReader.loadBeanDefinitions(document.getRootElement());
    }
}
