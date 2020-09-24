package pers.lxl.mylearnproject.frame.spring.annotation.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.lxl.mylearnproject.frame.spring.annotation.pojo.Landlord;
import pers.lxl.mylearnproject.frame.spring.annotation.pojo.StudentConfig;
import pers.lxl.mylearnproject.frame.spring.annotation.service.StudentService;
import pers.lxl.mylearnproject.frame.spring.annotation.service.StudentServiceImp;

public class TestSpring {
    public static void main(String[] args) {
        //通过注解方式初始化Spring IoC容器
        ApplicationContext context = new AnnotationConfigApplicationContext(StudentConfig.class);
        StudentService studentService = context.getBean("studentService", StudentServiceImp.class);
        studentService.printStudentInfo();
        //再次理解： @Autowired 注解表示在 Spring IoC 定位所有的 Bean 后，再根据类型寻找资源，然后将其注入。
        //过程： 定义 Bean ——》 初始化 Bean（扫描） ——》 根据属性需要从 Spring IoC 容器中搜寻满足要求的 Bean ——》 满足要求则注入
        //问题： IoC 容器可能会寻找失败，此时会抛出异常（默认情况下，Spring IoC 容器会认为一定要找到对应的 Bean 来注入到这个字段，但有些时候并不是一定需要，比如日志）
        //解决： 通过配置项 required 来改变，比如 @Autowired(required = false)
        //自动装配的歧义性（@Primary和@Qualifier）
        //BeanFactory 的定义，它存在一个按照类型获取 Bean 的方法，现在有两个 Srouce 类型的资源,显然通过 Source.class 作为参数无法判断使用哪个类实例进行返回，这就是自动装配的歧义性。
        //
        //为了消除歧义性，Spring 提供了两个注解：
        //@Primary 注解：
        //代表首要的，当 Spring IoC 检测到有多个相同类型的 Bean 资源的时候，会优先注入使用该注解的类。
        //问题：该注解只是解决了首要的问题，但是并没有选择性的问题
        //@Qualifier 注解：
        //上面所谈及的歧义性，一个重要的原因是 Spring 在寻找依赖注入的时候是按照类型注入引起的。
        // 除了按类型查找 Bean，Spring IoC 容器最底层的接口 BeanFactory 还提供了按名字查找的方法，
        // 如果按照名字来查找和注入不就能消除歧义性了吗？

        // 在 pojo 包下扫描
        ApplicationContext context1 = new AnnotationConfigApplicationContext("pojo");
        // 因为这里获取到的 Bean 就是 String 类型所以直接输出
        System.out.println(context.getBean("testBean"));

        //aop测试
        ApplicationContext context3 =
                new ClassPathXmlApplicationContext("application1.xml");
        Landlord landlord = (Landlord) context3.getBean("landlord", Landlord.class);
        landlord.service();


    }
}
