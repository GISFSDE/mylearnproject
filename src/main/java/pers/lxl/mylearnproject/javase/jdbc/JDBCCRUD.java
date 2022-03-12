package pers.lxl.mylearnproject.javase.jdbc;


import java.sql.*;

/**JDBC（Java DataBase Connectivity）Java程序访问数据库的标准接口
* URL是由数据库厂商指定的格式，例如，MySQL的URL是：
jdbcsprig:mysql://<hostname>:<port>/<db>?key1=value1&key2=value2
假设数据库运行在本机localhost，端口使用标准的3306，数据库名称是learnjdbc，那么URL如下：
jdbcsprig:mysql://localhost:3306/learnjdbc?useSSL=false&characterEncoding=utf8


 步骤：
 1.获取链接
 2.通过Connection提供的createStatement()方法创建一个Statement对象，用于执行一个查询；
 3.执行Statement对象提供的executeQuery("SELECT * FROM students")并传入SQL语句，执行查询并获得返回的结果集，使用ResultSet来引用这个结果集；
 4.反复调用ResultSet的next()方法并读取每一行结果。
 */
public class JDBCCRUD {
    public static void main(String[] args) throws SQLException {
        //?serverTimezone=GM解决TserverTimezone
        String JDBC_URL = "jdbcsprig:mysql://localhost:3306/learnjdbc?serverTimezone=GMT";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "root";

        //获取连接
//        Connection conn = null;
//            try {
//                conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)){
//            //访问数据库
//            try (Statement stmt = conn.createStatement()) {//Statement对象，用于执行一个查询
//                //ResultSet来引用这个结果集
//                //反复调用ResultSet的next()方法并读取每一行结果
//                try (ResultSet rs = stmt.executeQuery("SELECT id, grade, name, gender FROM students WHERE gender=1")) {
//                    while (rs.next()) {
//                        long id = rs.getLong(1); // 注意：索引从1开始
//                        long grade = rs.getLong(2);
//                        String name = rs.getString(3);
//                        int gender = rs.getInt(4);
//                        System.out.println(id+grade+name+gender);
//                    }
//                }
//            }
        //Statment和ResultSet都是需要关闭的资源，因此嵌套使用try (resource)确保及时关闭；
        //rs.next()用于判断是否有下一行记录，如果有，将自动把当前行移动到下一行（一开始获得ResultSet时当前行不是第一行）；
        //ResultSet获取列时，索引从1开始而不是0；
        //必须根据SELECT的列的对应位置来调用getLong(1)，getString(2)这些方法，否则对应位置的数据类型不对，将报错。
        //关闭连接,try之后不需要了
        // conn.close();

//        }



        //--SQL注入--   比如pass = " OR pass='"
//            避免一：针对所有字符串参数进行转义，但是转义很麻烦，而且需要在任何使用SQL的地方增加转义代码
//            避免二：使用PreparedStatement。使用PreparedStatement可以完全避免SQL注入的问题，因为PreparedStatement始终使用?作为占位符，
//            并且把数据连同SQL本身传给数据库，这样可以保证每次传给数据库的SQL语句是相同的，只是占位符的数据不同，还能高效利用数据库本身对查询的缓存。
        //--正确如下--CRUD：Create，Retrieve，Update和Delete
        /**原始次序---》获取连接--》创建Statement对象执行操作--》executeQuery传入sql语句，获取结果--》ResultSet的next()读取结果--》关闭连接（try后不用，自动关闭）
        prepareStatement次序---》获取连接--》创建prepareStatement对象执行操作--》executeQuery传入sql语句，获取结果--》ResultSet的next()读取结果
        查询*/
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT id, grade, name, gender FROM students WHERE gender=? AND grade=?")) {
                // 注意：索引从1开始
                ps.setObject(1, "B");
                ps.setObject(2, 2);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        long id = rs.getLong("id");
                        long grade = rs.getLong("grade");
                        String name = rs.getString("name");
                        String gender = rs.getString("gender");
                        System.out.println(id + " " + grade + " " + name + " " + gender);
                    }
                }
            }
        }
        //--插入--
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (PreparedStatement ps = conn.prepareStatement(
                    //---插入并获取自增主键的值---Statement.RETURN_GENERATED_KEYS
                    "INSERT INTO students (id,grade,name,gender) VALUES (?,?,?,?)",Statement.RETURN_GENERATED_KEYS)) {
                    ps.setObject(1, 994);
                    ps.setObject(2, 1);
                    ps.setObject(3, "LXL");
                    ps.setObject(4, 1);
                    //插入记录数量
                    int n = ps.executeUpdate();
                try (ResultSet resultSet = ps.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        //索引从1开始
                        long id = resultSet.getLong(1);
                        System.out.println(id);
                        System.out.println(ps);
                        System.out.println(n);
                    }
                }
            }
        }
        //--删除--
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (PreparedStatement ps = connection.prepareStatement("DELETE FROM students WHERE identity =?")) {
                ps.setObject(1,999);
                int n = ps.executeUpdate();//删除行数
            }
        }

            //--更新--
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (PreparedStatement ps = connection.prepareStatement("UPDATE students SET name =? WHERE id =?")) {
                ps.setObject(1,"Bob");
                ps.setObject(2,22);
                int n = ps.executeUpdate();//返回更新行数
            }
        }


    }

}
