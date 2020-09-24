package pers.lxl.mylearnproject.frame.spring.data.springjdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.lxl.mylearnproject.frame.spring.data.pojo.Student;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("application1.xml");
        JDBCtest jdbc = (JDBCtest) context.getBean("jdbc");
        Student student = jdbc.getOne(1);

        System.out.println(student.getId());
        System.out.println(student.getName());
    }
}
