package pers.lxl.mylearnproject.frame.spring.ioc_di;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.lxl.mylearnproject.frame.spring.ioc_di.pojo.JuiceMaker;
import pers.lxl.mylearnproject.frame.spring.ioc_di.pojo.Source;


public class TestSpring {

    @Test
    public void test(){
        //ClassPathXmlApplicationContext会自动从classpath中查找指定的XML配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext(
               // new String[]{"applicationContext.xml"}
                "application.xml"
        );

        Source source = (Source) context.getBean("source");
        System.out.println(source.getFruit());
        System.out.println(source.getSugar());
        System.out.println(source.getSize());

        JuiceMaker juiceMaker = (JuiceMaker) context.getBean("juickMaker");
        System.out.println(juiceMaker.makeJuice());
    }
}

