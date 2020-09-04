package pers.lxl.mylearnproject.frame.spring.annotation.pojo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//相当于 XML 文件的根元素，必须要，有了才能解析其中的 @Bean 注解
public class BeanTester {
    //问题： 以上都是通过 @Component 注解来装配 Bean ，并且只能注解在类上，当你需要引用第三方包的（jar 文件），而且往往并没有这些包的源码，这时候将无法为这些包的类加入 @Component 注解，让它们变成开发环境中的 Bean 资源。
    //解决方案：
    //1.自己创建一个新的类来扩展包里的类，然后再新类上使用 @Component 注解，但这样很 low
    //2.使用 ---@Bean 注解---，注解到方法之上，使其成为 Spring 中返回对象为 Spring 的 Bean 资源。
    @Bean(name = "testBean")
    //@Bean4 个配置项：
    //name： 是一个字符串数组，允许配置多个 BeanName
    //autowire： 标志是否是一个引用的 Bean 对象，默认值是 Autowire.NO
    //initMethod： 自定义初始化方法
    //destroyMethod： 自定义销毁方法
    //好处就是能够动态获取一个 Bean 对象，能够根据环境不同得到不同的 Bean 对象。
    // 或者说将 Spring 和其他组件分离（其他组件不依赖 Spring，但是又想 Spring 管理生成的 Bean）

    //Bean 的作用域
    //在默认的情况下，Spring IoC 容器只会对一个 Bean 创建一个实例，但有时候，
    // 我们希望能够通过 Spring IoC 容器获取多个实例，我们可以通过 @Scope 注解或者 <bean> 元素中的 scope 属性来设置
    //// XML 中设置作用域
    //<bean id="" class="" scope="prototype" />
    //// 使用注解设置作用域
    //@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    //Spring 提供了 5 种作用域，它会根据情况来决定是否生成新的对象：
    //
    //作用域类别	                描述
    //singleton(单例)	        在Spring IoC容器中仅存在一个Bean实例 （默认的scope）
    //prototype(多例)	        每次从容器中调用Bean时，都返回一个新的实例，即每次调用getBean()时 ，相当于执行new XxxBean()：不会在容器启动时创建对象
    //request(请求)	            用于web开发，将Bean放入request范围 ，request.setAttribute("xxx") ， 在同一个request 获得同一个Bean
    //session(会话)	            用于web开发，将Bean 放入Session范围，在同一个Session 获得同一个Bean
    //globalSession(全局会话)	一般用于 Porlet 应用环境 , 分布式系统存在全局 session 概念（单点登录），如果不是 porlet 环境，globalSession 等同于 Session
    //在开发中主要使用 scope="singleton"、scope="prototype"，对于MVC中的Action使用prototype类型，其他使用singleton，Spring容器会管理 Action 对象的创建,此时把 Action 的作用域设置为 prototype.
    public String test() {
        String string = "测试@Bean注解";
        return string;
    }

    //Spring 还提供了更灵活的注入方式，那就是--- Spring 表达式---，实际上 Spring EL 远比以上注入方式都要强大，它拥有很多功能：
    //使用 Bean 的 id 来引用 Bean
    //调用指定对象的方法和访问对象的属性
    //进行运算
    //提供正则表达式进行匹配
    //集合配置
}
