package com.abc;

import javassist.*;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Modifier;

public class JavassistCompiler {
    public static void main(String[] args) throws Exception {
        // 获取CtClass实例的工具类   Class Type Class，字节码类型的class类
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = genericClass(pool);
        invokeInstance(ctClass);
    }

    private static CtClass genericClass(ClassPool pool) throws Exception {
        // 通过classPool生成一个com.abc.Person类的字节码类
        CtClass ctClass = pool.makeClass("com.abc.Person");

        // 初始化这个字节码类
        // 向ctClass中添加private String name;
        CtField nameField = new CtField(pool.getCtClass("java.lang.String"), "name", ctClass);
        nameField.setModifiers(Modifier.PRIVATE);
        ctClass.addField(nameField);

        // 向ctClass中添加private int age;
        CtField ageField = new CtField(pool.getCtClass("int"), "age", ctClass);
        ageField.setModifiers(Modifier.PRIVATE);
        ctClass.addField(ageField);

        // 向ctClass中初始化getter and setter
        ctClass.addMethod(CtNewMethod.getter("getName",nameField));
        ctClass.addMethod(CtNewMethod.setter("setName",nameField));
        ctClass.addMethod(CtNewMethod.getter("getAge",ageField));
        ctClass.addMethod(CtNewMethod.setter("setAge",ageField));

        // 向ctClass中初始化无参构造器
        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{}, ctClass);
        String body = "{\nname=\"zhangsan\";\nage=23;\n}";
        ctConstructor.setBody(body);
        ctClass.addConstructor(ctConstructor);

        // 向ctClass中初始化业务方法
        CtMethod ctMethod = new CtMethod(CtClass.voidType, "personInfo", new CtClass[]{}, ctClass);
        ctMethod.setModifiers(Modifier.PUBLIC);
        StringBuffer buffer = new StringBuffer();
        buffer.append("{\nSystem.out.println(\"name=\"+name);\n");
        buffer.append("System.out.println(\"age=\"+age);\n}");
        ctMethod.setBody(buffer.toString());
        ctClass.addMethod(ctMethod);

        // 将生成的字节码写入到.class文件
        byte[] bytes = ctClass.toBytecode();
        File file = new File("/Users/conglongli/Documents/workspace/sourceCode/Person.class");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bytes);
        fos.close();

        return ctClass;
    }

    private static void invokeInstance(CtClass ctClass) throws Exception {
        // 通过ctClass获取到其对应的Class
        Class<?> clazz = ctClass.toClass();
        Object instance = clazz.newInstance();
        instance.getClass()
                .getMethod("personInfo", new Class[]{})
                .invoke(instance, new Object[]{});
    }
}
