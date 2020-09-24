package pers.lxl.mylearnproject.frame.spring.data.jdbc;


import pers.lxl.mylearnproject.frame.spring.data.pojo.Student;
import pers.lxl.mylearnproject.frame.spring.data.util.DBUtil;

import java.sql.*;

public class OldJDBC {
    public Student getOne(int id) {
        String sql = "SELECT id,name FROM student WHERE id = ?";
        Student student = null;
        //声明JDBC变量
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //注册驱动程序
//            Class.forName("com.mysql.jdbc.Driver");//This is deprecated
//            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取连接
//            con= DriverManager.getConnection("jdbc:mysql://localhost/student?serverTimezone=GMT", "root", "724111");
            // 获取连接
            con = DBUtil.getConnection();
            //预编译sql
            ps = con.prepareStatement(sql);
            //设置参数
            ps.setInt(1,id);
            //执行sql
            rs=ps.executeQuery();
            //组装结果集返回POJO
            if (rs.next()){
                student=new Student();
                student.setId(rs.getInt(1));
                student.setName(rs.getString(1));
            }

//        } catch (ClassNotFoundException |SQLException e) {
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭数据库
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (con != null && con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return student;
    }

    public static void main(String[] args) {
        OldJDBC oj=new OldJDBC();
        System.out.println(oj.getOne(1).getName());
    }
}
