package com.lcl.galaxy.springmvc.frame.springframe.reader;

import com.lcl.galaxy.springmvc.frame.springframe.domain.MyBeanDefinition;
import com.lcl.galaxy.springmvc.frame.springframe.domain.MyPropertyValue;
import com.lcl.galaxy.springmvc.frame.springframe.domain.MyRuntimeBeanReference;
import com.lcl.galaxy.springmvc.frame.springframe.domain.MyTypedStringValue;
import com.lcl.galaxy.springmvc.frame.springframe.register.MyBeanDefinitionRegisty;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Element;

import java.lang.reflect.Field;
import java.util.List;

@Slf4j
public class MyXmlBeanDefinitionDocumentReader {
    
    private MyBeanDefinitionRegisty registy;
    
    public MyXmlBeanDefinitionDocumentReader(MyBeanDefinitionRegisty registy) {
        super();
        this.registy = registy;
    }

    /**
     * 按照spring标签语义进行解析
     * @param rootElement
     */
    public void loadBeanDefinitions(Element rootElement) {
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
            this.registy.registry(beanName, beanDefinition);
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
