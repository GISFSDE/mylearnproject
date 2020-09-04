package pers.lxl.mylearnproject.frame.spring.data.jdbcsprig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component("jdbc")
public class JDBCtest {
    @Autowired
    private DataSource dataSource;

    public Student getOne(int stuID) throws SQLException {
        String sql = "SELECT id,name FROM student WHERE id= " + stuID;
        Student student = new Student();
        Connection conn = dataSource.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()) {
            student.setId(rs.getInt("id"));
            student.setName(rs.getString("name"));
        }
        return student;

    }
}
