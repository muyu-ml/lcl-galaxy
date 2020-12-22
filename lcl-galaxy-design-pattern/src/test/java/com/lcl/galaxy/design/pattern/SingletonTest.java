package com.lcl.galaxy.design.pattern;

import com.lcl.galaxy.design.pattern.singleton.DoubleCheckSingleAnimal;
import com.lcl.galaxy.design.pattern.singleton.StaticInnerClass;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@Slf4j
@SpringBootTest
public class SingletonTest {

    @Test
    public void reflectAttackTest() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Constructor constructor = DoubleCheckSingleAnimal.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        DoubleCheckSingleAnimal a1 = (DoubleCheckSingleAnimal) constructor.newInstance();
        DoubleCheckSingleAnimal a2 = (DoubleCheckSingleAnimal) constructor.newInstance();

        a1.setName("lcl");
        a2.setName("lcl");
        log.info("a1============={}==========",a1);
        log.info("a2============={}==========",a2);
        log.info("a1.equals(a2)============={}==========",a1.equals(a2));
    }

    @Test
    public void serializationAttackTest() throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("aaa"));
        DoubleCheckSingleAnimal a1 = DoubleCheckSingleAnimal.getSingleAnima();
        oos.writeObject(a1);

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("aaa"));
        DoubleCheckSingleAnimal a2 = (DoubleCheckSingleAnimal) ois.readObject();

        a1.setName("lcl");
        a2.setName("lcl");
        log.info("a1============={}==========",a1);
        log.info("a2============={}==========",a2);
        log.info("a1.equals(a2)============={}==========",a1.equals(a2));
    }



    @Test
    public void staticInnerClassAttackTest() throws Exception {
        Constructor constructor = StaticInnerClass.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        StaticInnerClass a1 = (StaticInnerClass) constructor.newInstance();
        StaticInnerClass a2 = (StaticInnerClass) constructor.newInstance();

        log.info("a1============={}==========",a1);
        log.info("a2============={}==========",a2);
        log.info("a1.equals(a2)============={}==========",a1.equals(a2));

        log.info("a1.getStaticInnerClass()============={}==========",a1.getStaticInnerClass());
        log.info("a2.getStaticInnerClass()============={}==========",a2.getStaticInnerClass());
        log.info("a1.getStaticInnerClass().equals(a2.getStaticInnerClass())============={}==========",a1.getStaticInnerClass().equals(a2.getStaticInnerClass()));
    }

}
