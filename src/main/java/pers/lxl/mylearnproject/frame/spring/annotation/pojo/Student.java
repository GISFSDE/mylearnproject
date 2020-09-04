package pers.lxl.mylearnproject.frame.spring.annotation.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(value = "student1")
//表示 Spring IoC 会把这个类扫描成一个 bean 实例，而其中的 value 属性代表这个类在 Spring 中的 id，这就相当于在 XML 中定义的 Bean 的 id：<bean id="student1" class="pojo.Student" />，也可以简写成 @Component("student1")，甚至直接写成 @Component ，对于不写的，Spring IoC 容器就默认以类名来命名作为 id，只不过首字母小写，配置到容器中。
public class Student {
    @Value("1")//表示值的注入，跟在 XML 中写 value 属性是一样的。不能注入对象
    int id;
    @Value("student_name_1")
    String name;
/*同<bean name="student1" class="pojo.Student">
    <property name="id" value="1" />
    <property name="name" value="student_name_1"/>
</bean>*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Autowired//@Autowired 注解不仅仅能配置在属性之上，还允许方法配置，常见的 Bean 的 setter 方法也可以使用它来完成注入，总之一切需要 Spring IoC 去寻找 Bean 资源的地方都可以用到
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

