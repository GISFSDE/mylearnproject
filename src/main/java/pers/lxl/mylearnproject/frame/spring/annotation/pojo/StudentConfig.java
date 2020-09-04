package pers.lxl.mylearnproject.frame.spring.annotation.pojo;

import org.springframework.context.annotation.ComponentScan;
/*该类和 Student 类位于同一包名下
@ComponentScan注解：代表进行扫描，默认是扫描当前包的路径(更多的时候我们希望的是可以扫描我们指定的类)，扫描所有带有 @Component 注解的 POJO。*/
//@ComponentScan
//两个配置项：
//basePackages：它是由 base 和 package 两个单词组成的，而 package 还是用了复数，意味着它可以配置一个 Java 包的数组，Spring 会根据它的配置扫描对应的包和子包，将配置好的 Bean 装配进来
//basePackageClasses：它由 base、package 和 class 三个单词组成，采用复数，意味着它可以配置多个类， Spring 会根据配置的类所在的包，为包和子包进行扫描装配对应配置的 Bean
@ComponentScan(basePackages = {"pers.lxl.mylearnproject.frame.spring.annotation.pojo","pers.lxl.mylearnproject.frame.spring.annotation.service"})//项目底层包可以只写包名
// 或者也可以在 XML 文件中声明去哪里做扫描
//<context:component-scan base-package="pojo" />
//<context:component-scan base-package="service" />
//@ComponentScan(basePackageClasses = pojo.Student.class)
//【basePackages】 的可读性会更好一些，所以在项目中会优先选择使用它，但是在需要大量重构的工程中，尽量不要使用【basePackages】，因为很多时候重构修改包名需要反复地配置，而 IDE 不会给你任何的提示，而采用【basePackageClasses】会有错误提示。
public class StudentConfig {

}
