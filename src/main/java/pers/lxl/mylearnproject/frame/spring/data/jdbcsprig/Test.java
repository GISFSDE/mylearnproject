package pers.lxl.mylearnproject.frame.spring.data.jdbcsprig;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        JDBCtest jdbCtest = (JDBCtest) context.getBean("jdbc");
        Student s= jdbCtest.getOne(1);
        System.out.println(s.getId());
        System.out.println(s.getName());
    }
}
