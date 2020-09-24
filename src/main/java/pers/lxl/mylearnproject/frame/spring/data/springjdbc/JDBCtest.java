package pers.lxl.mylearnproject.frame.spring.data.springjdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import pers.lxl.mylearnproject.frame.spring.data.pojo.Student;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*Spring Jdbc*/
//@Component("jdbc")
//public class JDBCtest {
//    @Autowired
//    private DataSource dataSource1;
//
//    public Student getOne(int stuID) throws SQLException {
//
//        String sql = "SELECT id, name FROM student WHERE id = " + stuID;
//        Student student = new Student();
//        Connection con = dataSource1.getConnection();
//        Statement st = con.createStatement();
//        ResultSet rs = st.executeQuery(sql);
//        if (rs.next()) {
//            student.setId(rs.getInt("id"));
//            student.setName(rs.getString("name"));
//        }
//        return student;
//    }
//
//
//}
/*Jdbc Template*/
@Component("jdbc")
public class JDBCtest {

    @Autowired(required = false)
    private JdbcTemplate jdbcTemplate;

    public Student getOne(int stuID) throws SQLException {

        String sql = "SELECT id, name FROM student WHERE id = ?";
        Student student = jdbcTemplate.queryForObject(sql, new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet resultSet, int i) throws SQLException {
                Student stu = new Student();
                stu.setId(resultSet.getInt("id"));
                stu.setName(resultSet.getString("name"));
                return stu;
            }
        }, 1);
        return student;
    }


    /**
     * 增加一条数据
     *
     * @param student
     */
    public void add(Student student) {
        this.jdbcTemplate.update("INSERT INTO student(id,name) VALUES(?,?)",
                student.getId(), student.getName());
    }

    /**
     * 更新一条数据
     *
     * @param student
     */
    public void update(Student student) {
        this.jdbcTemplate.update("UPDATE student SET name = ? WHERE id = ?",
                student.getName(), student.getId());
    }

    /**
     * 删除一条数据
     *
     * @param id
     */
    public void delete(int id) {
        this.jdbcTemplate.update("DELETE FROM student WHERE id = ?",
                id);
    }
}