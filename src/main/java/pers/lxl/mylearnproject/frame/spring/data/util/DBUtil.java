package pers.lxl.mylearnproject.frame.spring.data.util;

import java.sql.*;

public class DBUtil {
        static String ip = "127.0.0.1";
        static int port = 3306;
        static String database = "student";
        static String encoding = "UTF-8";
        static String loginName = "root";
        static String password = "724111";

        static {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        public static Connection getConnection() throws SQLException {
            String url = String.format("jdbc:mysql://%s:%d/%s?serverTimezone=GMT", ip, port, database, encoding);
            return DriverManager.getConnection(url, loginName, password);
        }

    public static ResultSet getResultSet(String sql, Object[] objects) throws SQLException {

        ResultSet rs = null;
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            // 根据传递进来的参数，设置 SQL 占位符的值
            for (int i = 0; i < objects.length; i++) {
                ps.setObject(i + 1, objects[i]);
            }
            // 执行 SQL 语句并接受结果集
            rs = ps.executeQuery();
        }
        // 返回结果集
        return rs;
    }
    }

