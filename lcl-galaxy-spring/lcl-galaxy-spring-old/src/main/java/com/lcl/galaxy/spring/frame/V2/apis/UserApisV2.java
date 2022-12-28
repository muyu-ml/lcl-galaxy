package com.lcl.galaxy.spring.frame.V2.apis;

import com.lcl.galaxy.spring.frame.V2.domain.*;
import com.lcl.galaxy.spring.frame.V2.service.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.Constructor;
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

    /**
     * 根据bean名称获取bean对象
     * @param beanName
     * @return
     */
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

    /**
     * 创建bean
     * @param beanDefinition
     * @return
     */
    private Object createBean(MyBeanDefinition beanDefinition) {
        
        String clazzName = beanDefinition.getClazzName();
        try {
            Class<?> clazz = Class.forName(clazzName);
            if(clazz == null){
                return null;
            }
            Constructor<?> constructor = clazz.getConstructor();
            Object object = constructor.newInstance();
            populateBean(object, beanDefinition);
            initMethod();
           return object;
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 处理初始化方法
     */
    private void initMethod() {
    }

    /**
     * 为对象赋值
     * @param object
     * @param beanDefinition
     */
    private void populateBean(Object object, MyBeanDefinition beanDefinition) {
        List<MyPropertyValue> propertyValueList = beanDefinition.getPropertyValueList();
        for (MyPropertyValue propertyValue: propertyValueList) {
            String name = propertyValue.getName();
            Object value = propertyValue.getTypedStringValue();
            Object valueToUse = null;
            if(value instanceof MyTypedStringValue){
                MyTypedStringValue myTypedStringValue = (MyTypedStringValue) value;
                String stringValue = myTypedStringValue.getValue();
                Class<?> targetType = myTypedStringValue.getTargetType();
                if(targetType == Integer.class){
                    valueToUse = Integer.parseInt(stringValue);
                }else if(targetType == String.class){
                    valueToUse = String.valueOf(stringValue);
                }else{

                }
            }else if(value instanceof MyRuntimeBeanReference){
                MyRuntimeBeanReference myRuntimeBeanReference = (MyRuntimeBeanReference) value;
                valueToUse = getBean(myRuntimeBeanReference.getRef());
            }
            Class<?> clazz = object.getClass();
            try {
                Field declaredField = clazz.getDeclaredField(name);
                declaredField.setAccessible(true);
                declaredField.set(object, valueToUse);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 加载配置文件
     */
    public void init() {
        String location = "write-frame-beans.xml";
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(location);
        Document document = createDocument(inputStream);
        registerBeanDefinition(document.getRootElement());
    }

    /**
     * 输入流传唤为Document
     * @param inputStream
     * @return
     */
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

    /**
     * 加载配置文件
     * @param rootElement
     */
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

    /**
     * 解析自定义标签
     * @param element
     */
    private void parseCustomElement(Element element) {
    }

    /**
     * 解析bean标签，封装为Bedefinition，并将BeanDefinition放入map中
     * @param beanElement
     */
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

    /**
     * 为BeanDefinition的property集合赋值
     * @param beanDefinition
     * @param propertyElement
     */
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

    /**
     * 获取简单类型
     * @param clazzName
     * @param name
     * @return
     */
    private Class<?> getTypeFieldName(String clazzName, String name) {
        try {
            Class<?> clazz = Class.forName(clazzName);
            Field field = clazz.getDeclaredField(name);
            return field.getType();
        } catch (Exception e) {
            log.info("================== clazzName,name ====================,{}==========,{}",clazzName, name);
            e.printStackTrace();
        }
        return null;
    }


}
