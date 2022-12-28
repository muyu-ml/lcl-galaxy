package com.lcl.galaxy.springnative.reader;

import com.lcl.galaxy.springnative.registry.BeanDefinitionRegistry;
import com.lcl.galaxy.springnative.resource.Resource;
import com.lcl.galaxy.springnative.utils.DocumentReader;
import org.dom4j.Document;

import java.io.InputStream;

/**
 * 读取xml文件并封装成为 beanfactory
 */
public class XmlBeanDefinitionReader {
    private BeanDefinitionRegistry beanDefinitionRegistry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry){
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    public void loadBeanDefinitions(Resource resource){
        InputStream inputStream = resource.getResourceAsStream();
        Document document = DocumentReader.createDocument(inputStream);
        XmlBeanDefinitionDocumentReader documentReader = new XmlBeanDefinitionDocumentReader(beanDefinitionRegistry);
        documentReader.loadBeanDefinitions(document.getRootElement());
    }
}
