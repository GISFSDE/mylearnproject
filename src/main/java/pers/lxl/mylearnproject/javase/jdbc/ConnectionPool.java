package pers.lxl.mylearnproject.javase.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**我们在讲多线程的时候说过，创建线程是一个昂贵的操作，如果有大量的小任务需要执行，
并且频繁地创建和销毁线程，实际上会消耗大量的系统资源，往往创建和消耗线程所耗费的时间比执行任务的时间还长，所以，为了提高效率，可以用线程池。
类似的，在执行JDBC的增删改查的操作时，如果每一次操作都来一次打开连接，操作，关闭连接，
那么创建和销毁JDBC连接的开销就太大了。为了避免频繁地创建和销毁JDBC连接，
我们可以通过连接池（Connection Pool）复用已经创建好的连接。
JDBC连接池有一个标准的接口javax.sql.DataSource，注意这个类位于Java标准库中，但仅仅是接口。
要使用JDBC连接池，我们必须选择一个JDBC连接池的实现。常用的JDBC连接池有：
HikariCP
C3P0
BoneCP
Druid*/
public class ConnectionPool {
    //1.添加依赖
    //2.创建DataScourse实例
    public static void main(String[] args) throws SQLException {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbcsprig:mysql//localhost:3306/test");
        config.setUsername("root");
        config.setPassword("root");
        // 连接超时：1秒
        config.addDataSourceProperty("connnectionTime","1000");
        // 空闲超时：60秒
        config.addDataSourceProperty("idleTimeout","60000");
        // 最大连接数：10
        config.addDataSourceProperty("maxmumPoolSize","10");
        DataSource dataSource = new HikariDataSource(config);

//        原来是Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)
        try (Connection connection = dataSource.getConnection()) {

        }
    }

}
