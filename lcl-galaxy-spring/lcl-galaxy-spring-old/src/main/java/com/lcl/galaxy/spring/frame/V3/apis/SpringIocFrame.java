package com.lcl.galaxy.spring.frame.V3.apis;

import com.lcl.galaxy.spring.frame.V2.domain.UserDo;
import com.lcl.galaxy.spring.frame.V2.service.UserService;
import com.lcl.galaxy.spring.frame.V3.beanfactory.support.MyDefaultListableBeanFactory;
import com.lcl.galaxy.spring.frame.V3.reader.MyXmlBeanDefinitionReader;
import com.lcl.galaxy.spring.frame.V3.resource.MyClassPathResource;
import com.lcl.galaxy.spring.frame.V3.resource.MyResources;
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

    public void findUserById(String id, MyDefaultListableBeanFactory beanFactory) throws Exception {
        UserService userService = (UserService) beanFactory.getBean("userService");
        UserDo userDo = userService.findUserById(id);
        log.info("=================={}=================", userDo);
    }


}
