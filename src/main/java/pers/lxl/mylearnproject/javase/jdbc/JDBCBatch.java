package pers.lxl.mylearnproject.javase.jdbc;

import java.util.Collections;//注意两个区别
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/*通过一个循环来执行每个PreparedStatement虽然可行，但是性能很低。SQL数据库对SQL语句相同，
但只有参数不同的若干语句可以作为batch执行，即批量执行，这种操作有特别优化，速度远远快于循环执行每个SQL。*/
public class JDBCBatch {
    public static void main(String[] args) throws SQLException {
        Student students=new Student();
        students.setScore(45);
        students.setGender("1");
        students.setGrade(21);
        students.setName("Bof");
        String JDBC_URL = "jdbc:mysql://localhost:3306/learnjdbc?serverTimezone=GMT";//?serverTimezone=GM解决TserverTimezone
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "root";
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO students(name,gender, grade, score)VALUES (?,?,?,?)")) {
//                for (Student s : students) {
//                    ps.setObject(1,s.name);
//                    ps.setObject(2, s.gender);
//                    ps.setObject(3, s.grade);
//                    ps.setObject(4, s.score);
//                    ps.addBatch();
//                }
                int[] ns=ps.executeBatch();
                for (int n : ns) {
                    System.out.println(n+"inserted");
                }
            }
        }
    }

}
