package com.gisfsde.springbootlearn;


import com.gisfsde.springbootlearn.config.MyConfig;
import com.gisfsde.springbootlearn.entity.User;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;


/**
 * Spring Boot 项目通常有一个名为 *Application 的入口类，入口类里有一个 main 方法， 这个 main 方法其实就是一个标准的 Javay 应用的入口方法。
 * -@SpringBootApplication===@Configuration、@EnableAutoConfiguration、@ComponentScan
 * -@EnableAutoConfiguration 让 Spring Boot 根据类路径中的 jar 包依赖为当前项目进行自动配置，
 * 例如，添加了 spring-boot-starter-web 依赖，会自动添加 Tomcat 和 Spring MVC 的依赖，那么 Spring Boot 会对 Tomcat 和 Spring MVC 进行自动配置。
 * spring Boot 还会自动扫描 @SpringBootApplication 所在类的同级包以及下级包里的 Bean ，所以入口类建议就配置在 grounpID + arctifactID 组合的包名下（这里为 com.gisfsde.springbootlearn 包）
 * -@SpringBootApplication(scanBasePackages = "cn.lxl")  如需扫描主程序同级外的包则可添加scanBasePackages参数
 * @author Administrator
 */


@EnableSwagger2Doc
@SpringBootApplication
public class SpringbootlearnApplication {

	public static void main(String[] args) {
//        SpringApplication.run(SpringbootApplication.class, args); 返回IOC容器
		ConfigurableApplicationContext run = SpringApplication.run(SpringbootlearnApplication.class, args);
//        查看所有组件
//        String[] beanDefinitionNames = run.getBeanDefinitionNames();
//        for (String name : beanDefinitionNames
//        ) {
//            System.out.println(name);
//        }

		//配置类本身也是组件,以类型获取组件
		MyConfig bean = run.getBean(MyConfig.class);
		System.out.println("MyConfig:"+bean);

//        有多个相同组件会报错NoUniqueBeanDefinitionException
//        User user = run.getBean(User.class);
//        System.out.println(user);

//      以名称判断是否有组件
		boolean user01 = run.containsBean("user01");
		System.out.println("包含user01 bean:"+user01);
//      条件注解测试
		boolean student = run.containsBean("student");
		System.out.println("包含student bean:"+student);

//      @ImportResource("classpath:beans.xml")将配置文件中的bean注入容器
		boolean bean1 = run.containsBean("bean1");
		System.out.println("包含bean1 bean:"+bean1);

//      配置绑定,需要@Component
//      @ConfigurationProperties(prefix = "student")//表示获取所有配置文件中前缀为 sutdent 的配置信息，实体类中使用
//      当不能在原类中添加@component注解时（比如用他人包时），则在使用的地方添加此注解,作用是开启student配置绑定功能且将其注入到容器中
//      @EnableConfigurationProperties(StudentProperties.class)
		Object student1 = run.getBean("student1");
		System.out.println("包含student1 bean:"+student1);

//      通过类型获取组件
		String[] beanNamesForType = run.getBeanNamesForType(User.class);
		for (String s : beanNamesForType
		) {
			System.out.println("User类型组件：" + s);
		}

//      获取组件数量
		int beanDefinitionCount = run.getBeanDefinitionCount();
		System.out.println("初始组件数量："+beanDefinitionCount);

	}

}


//运⾏ SpringBoot 有哪⼏种⽅式？

//打包⽤命令或者放到容器中运⾏。 ⽤ Maven/Gradle 插件运⾏。
//直接执⾏ main ⽅法运⾏。
//SpringBoot ⽀持 Java Util Logging，Log4j2，Logback 作为⽇志框架，如果你使⽤ Starters 启动器，SpringBoot 将使⽤ Logback 作为默认框架。
//⽤ Maven/Gradle 插件运⾏。可运行thtmeleaf与jsp同时存在的jsp，
