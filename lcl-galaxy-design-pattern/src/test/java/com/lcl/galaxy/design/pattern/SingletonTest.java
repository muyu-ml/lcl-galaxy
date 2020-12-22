package com.lcl.galaxy.design.pattern;

import com.lcl.galaxy.design.pattern.singleton.DoubleCheckSingleAnimal;
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
    public void serializationAttackTest() throws IOException, ClassNotFoundException {
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

}
