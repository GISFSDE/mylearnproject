package pers.lxl.mylearnproject.frame.spring.aop;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.lxl.mylearnproject.frame.spring.aop.service.ProductService;

/*AOP 即 Aspect Oriented Program 面向切面编程
首先，在面向切面编程的思想里面，把功能分为核心业务功能，和周边功能。
核心业务：比如登陆，增加数据，删除数据都叫核心业务
周边功能：比如性能统计，日志，事务管理等等
周边功能在 Spring 的面向切面编程AOP思想里，即被定义为切面
在面向切面编程AOP的思想里面，核心业务功能和切面功能分别独立进行开发，
然后把切面功能和核心业务功能 "编织" 在一起，这就叫AOP
AOP 的目的
AOP能够将那些与业务无关，却为业务模块所共同调用的逻辑或责任
（例如事务处理、日志管理、权限控制等）封装起来，便于减少系统的重复代码，
降低模块间的耦合度，并有利于未来的可拓展性和可维护性。
AOP 当中的概念：
切入点（Pointcut）
在哪些类，哪些方法上切入（where）
通知（Advice）
在方法执行的什么实际（when:方法前/方法后/方法前后）做什么（what:增强的功能）
切面（Aspect）
切面 = 切入点 + 通知，通俗点就是：在什么时机，什么地方，做什么增强！
织入（Weaving）
把切面加入到对象，并创建出代理对象的过程。（由 Spring 来完成）*/
public class AopTest {
    @Test
    public void test(){
    ApplicationContext context = new ClassPathXmlApplicationContext("application1.xml");
    ProductService s = (ProductService) context.getBean("productService");
    s.doSomeService();
    }
}
