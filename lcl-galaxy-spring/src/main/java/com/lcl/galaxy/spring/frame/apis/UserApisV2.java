package com.lcl.galaxy.spring.frame.apis;

import com.lcl.galaxy.spring.frame.domain.*;
import com.lcl.galaxy.spring.frame.service.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Data
public class UserApisV2 {

    private Map<String, Object> singletonObjects = new HashMap<>();
    private Map<String, MyBeanDefinition> beanDefinitions = new HashedMap();

    public void findUserById(String id) throws Exception {
        UserService userService = (UserService) getBean("userService");
        UserDo userDo = userService.findUserById(id);
        log.info("=================={}=================", userDo);
    }

    private Object getBean(String beanName) {
        Object object = singletonObjects.get(beanName);
        if (object != null) {
            return object;
        }

        MyBeanDefinition beanDefinition = beanDefinitions.get(beanName);
        if (beanDefinition == null || beanDefinition.getClazzName() == null) {
            return null;
        }

        if (beanDefinition.isSingleton()) {
            object = createBean(beanDefinition);
            this.singletonObjects.put(beanName, object);
        } else if (beanDefinition.isPrototype()) {
            object = createBean(beanDefinition);
        }
        return object;
    }

    private Object createBean(MyBeanDefinition beanDefinition) {
        return null;
    }


    public void init() {
        String location = "";
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(location);
        Document document = createDocument(inputStream);
        registerBeanDefinition(document.getRootElement());
    }

    private void registerBeanDefinition(Element rootElement) {
        List<Element> elements = rootElement.elements();
        for (Element element : elements) {
            String name = element.getName();
            if ("bean".equals(name)) {
                parseDefaultElement(element);
            } else {
                parseCustomElement(element);
            }
        }
    }

    private void parseCustomElement(Element element) {
    }

    private void parseDefaultElement(Element beanElement) {
        if (beanElement == null) {
            return;
        }

        String id = beanElement.attributeValue("id");
        String name = beanElement.attributeValue("name");
        String clazzName = beanElement.attributeValue("class");
        if (clazzName == null || "".equals(clazzName)) {
            return;
        }

        String initMethod = beanElement.attributeValue("initMethod");
        String scope = beanElement.attributeValue("scope");
        scope = scope != null ? scope : "singleton";
        String beanName = id == null ? name : id;
        Class<?> clazzType = null;
        try {
            clazzType = Class.forName(clazzName);
            beanName = beanName == null ? clazzType.getName() : beanName;
            MyBeanDefinition beanDefinition = new MyBeanDefinition(clazzName, beanName);
            beanDefinition.setInitMethod(initMethod);
            beanDefinition.setScope(scope);
            List<Element> propertyElements = beanElement.elements();
            for (Element propertyElement : propertyElements) {
                parsePropertyElement(beanDefinition, propertyElement);
            }
            beanDefinitions.put(beanName, beanDefinition);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void parsePropertyElement(MyBeanDefinition beanDefinition, Element propertyElement) {
        String name = propertyElement.attributeValue("name");
        String value = propertyElement.attributeValue("value");
        String ref = propertyElement.attributeValue("ref");

        if(value != null && !"".equals(value) && ref != null && !"".equals(ref)){
            return;
        }

        MyPropertyValue pv = null;
        if(value != null && !"".equals(value)){
            MyTypedStringValue typedStringValue = new MyTypedStringValue(value);
            Class<?> targetType = getTypeFieldName(beanDefinition.getClazzName(), name);
            typedStringValue.setTargetType(targetType);
            pv = new MyPropertyValue(name, typedStringValue);
            beanDefinition.addPropertyValue(pv);
        }else{
            MyRuntimeBeanReference runtimeBeanReference = new MyRuntimeBeanReference(ref);
            pv = new MyPropertyValue(name, runtimeBeanReference);
            beanDefinition.addPropertyValue(pv);
        }
    }

    private Class<?> getTypeFieldName(String clazzName, String name) {
        try {
            Class<?> clazz = Class.forName(clazzName);
            Field field = clazz.getField(name);
            return field.getType();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Document createDocument(InputStream inputStream) {
        Document document = null;
        SAXReader saxReader = new SAXReader();
        try {
            document = saxReader.read(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return document;
    }
}
