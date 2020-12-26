package com.lcl.galaxy.springmvc.frame.springframe.IocFrame;

import com.lcl.galaxy.springmvc.frame.springframe.beanfactory.support.MyDefaultListableBeanFactory;
import com.lcl.galaxy.springmvc.frame.springframe.reader.MyXmlBeanDefinitionReader;
import com.lcl.galaxy.springmvc.frame.springframe.resource.MyClassPathResource;
import com.lcl.galaxy.springmvc.frame.springframe.resource.MyResources;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class SpringIocFrame {

    public MyDefaultListableBeanFactory init(){
        String location = "write-frame-beans.xml";

        //加载配置文件
        MyResources resources = new MyClassPathResource(location);

        //创建BeanFactory
        MyDefaultListableBeanFactory beanFactory = new MyDefaultListableBeanFactory();

        //
        MyXmlBeanDefinitionReader reader = new MyXmlBeanDefinitionReader(beanFactory);

        reader.loadBeanDefinitions(resources);

        return beanFactory;
    }


}
