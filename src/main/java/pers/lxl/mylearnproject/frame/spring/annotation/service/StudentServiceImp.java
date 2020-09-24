package pers.lxl.mylearnproject.frame.spring.annotation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.lxl.mylearnproject.frame.spring.annotation.pojo.Student;
@Component("studentService")
public class StudentServiceImp implements StudentService{
    @Autowired//注解表示在 Spring IoC 定位所有的 Bean 后，再根据类型寻找资源，然后将其注入。
    //问题： IoC 容器可能会寻找失败，此时会抛出异常（默认情况下，Spring IoC 容器会认为一定要找到对应的 Bean 来注入到这个字段，但有些时候并不是一定需要，比如日志）
    //解决： 通过配置项 required 来改变，比如 @Autowired(required = false)
    //@Qualifier("source1"):按照名字来查找和注入消除歧义性
    //@Primary:Spring IoC 检测到有多个相同类型的 Bean 资源的时候，会优先注入使用该注解的类
    private Student student=null;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


    public void printStudentInfo() {
        System.out.println("学生的 id 为：" + student.getId());
        System.out.println("学生的 name 为：" + student.getName());
    }
}
