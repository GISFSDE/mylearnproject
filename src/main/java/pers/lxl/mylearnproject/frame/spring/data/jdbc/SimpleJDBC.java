package pers.lxl.mylearnproject.frame.spring.data.jdbc;

import pers.lxl.mylearnproject.frame.spring.data.pojo.Student;
import pers.lxl.mylearnproject.frame.spring.data.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SimpleJDBC {
    public Student getOne(int id) throws SQLException {

        String sql = "SELECT id,name FROM student WHERE id = ?";
        Object[] objects = {id};
        Student student = null;
        try (ResultSet rs = DBUtil.getResultSet(sql, objects);) {
            student.setId(rs.getInt(1));
            student.setName(rs.getString(1));
        }
        return student;
    }

    public static void main(String[] args) throws SQLException {
        SimpleJDBC sj = new SimpleJDBC();
        System.out.println(sj.getOne(1).getName());
    }
}
