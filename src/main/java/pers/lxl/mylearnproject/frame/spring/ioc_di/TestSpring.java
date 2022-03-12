package pers.lxl.mylearnproject.frame.spring.ioc_di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;
import pers.lxl.mylearnproject.frame.spring.ioc_di.pojo.JuiceMaker;
import pers.lxl.mylearnproject.frame.spring.ioc_di.pojo.Source;
/*-----装配 Bean----
1.最优先：通过隐式 Bean 的发现机制和自动装配的原则。
    基于约定由于配置的原则，这种方式应该是最优先的
    好处：减少程序开发者的决定权，简单又不失灵活。
2.其次：Java 接口和类中配置实现配置
    在没有办法使用自动装配原则的情况下应该优先考虑此类方法
    好处：避免 XML 配置的泛滥，也更为容易。
    典型场景：一个父类有多个子类，比如学生类有两个子类，一个男学生类和女学生类，通过 IoC 容器初始化一个学生类，容器将无法知道使用哪个子类去初始化，这个时候可以使用 Java 的注解配置去指定。
3.最后：XML 方式配置
    在上述方法都无法使用的情况下，那么也只能选择 XML 配置的方式。
    好处：简单易懂（当然，特别是对于初学者）
    典型场景：当使用第三方类的时候，有些类并不是我们开发的，我们无法修改里面的代码，这个时候就通过 XML 的方式配置使用了。
*/

public class TestSpring {
    @Test
    public void test(){
        //ClassPathXmlApplicationContext会自动从classpath中查找指定的XML配置文件
        //ClassPathXmlApplicationContext：读取classpath中的资源
        //FileSystemXmlApplicationContext: 读取指定路径的资源
        //XmlWebApplicationContext: 需要在Web的环境下才可以运行
            //XmlWebApplicationContext ac = new XmlWebApplicationContext(); // 这时并没有初始化容器
            //ac.setServletContext(servletContext); // 需要指定ServletContext对象
            //ac.setConfigLocation("/WEB-INF/applicationContext.xml"); // 指定配置文件路径，开头的斜线表示Web应用的根目录
            //ac.refresh(); // 初始化容器

        //BeanFactory 和 ApplicationContext 的区别：
        //BeanFactory：是Spring中最底层的接口，只提供了最简单的IoC功能,负责配置，创建和管理bean。
        //在应用中，一般不使用 BeanFactory，而推荐使用ApplicationContext（应用上下文），原因如下。
        //ApplicationContext：
        //1.继承了 BeanFactory，拥有了基本的 IoC 功能；
        //2.除此之外，ApplicationContext 还提供了以下功能：
        //① 支持国际化；
        //② 支持消息机制；
        //③ 支持统一的资源加载；
        //④ 支持AOP功能；
        ApplicationContext context = new ClassPathXmlApplicationContext(
               // new String[]{"applicationContext.xml"}
                "application1.xml"
        );

        Source source = (Source) context.getBean("source");
        System.out.println(source.getFruit());
        System.out.println(source.getSugar());
        System.out.println(source.getSize());
        //BeanFactory
//【getBean】 对应了多个方法来获取配置给 Spring IoC 容器的 Bean。
//① 按照类型拿 bean：
//bean = (Bean) factory.getBean(Bean.class);
//注意：要求在 Spring 中只配置了一个这种类型的实例，否则报错。（如果有多个那 Spring 就懵了，不知道该获取哪一个）
//② 按照 bean 的名字拿 bean:
//bean = (Bean) factory.getBean("beanName");
//注意：这种方法不太安全，IDE 不会检查其安全性（关联性）
//③ 按照名字和类型拿 bean：（推荐）
//bean = (Bean) factory.getBean("beanName", Bean.class);
//【isSingleton】 用于判断是否单例，如果判断为真，其意思是该 Bean 在容器中是作为一个唯一单例存在的。而【isPrototype】则相反，如果判断为真，意思是当你从容器中获取 Bean，容器就为你生成一个新的实例。
//注意：在默认情况下，【isSingleton】为 ture，而【isPrototype】为 false
//关于 type 的匹配，这是一个按 Java 类型匹配的方式
//【getAliases】方法是获取别名的方法
        JuiceMaker juiceMaker = (JuiceMaker) context.getBean("juickMaker");
        System.out.println(juiceMaker.makeJuice());
    }
}

