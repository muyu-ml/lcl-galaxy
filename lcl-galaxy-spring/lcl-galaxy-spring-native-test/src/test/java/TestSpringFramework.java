import com.lcl.galaxy.springnative.beanfactory.support.DefaultListableBeanFactory;
import com.lcl.galaxy.springnative.reader.XmlBeanDefinitionReader;
import com.lcl.galaxy.springnative.resource.ClasspathResource;
import com.lcl.galaxy.springnative.resource.Resource;
import com.lcl.spring.nativetest.po.Student;
import org.junit.Test;

public class TestSpringFramework {

    @Test
    public void test(){
        // 1、指定 spring 配置文件路径
        String location = "beans.xml";
        // 将路径封装成资源
        Resource resource = new ClasspathResource(location);
        // 2、创建 BeanDefinitionRegistry
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 3、加载 spring 配置文件，并最终将解析出来的 BeanDefinition 注册到 BeanDefinitionRegistry
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);

        Student student = (Student) beanFactory.getBean("student");
        System.out.println("==========" + student.toString());
    }

}
