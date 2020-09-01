package pers.lxl.mylearnproject.javase.jdbc;


import java.sql.Connection;
import java.sql.SQLException;

import static org.springframework.data.mongodb.core.aggregation.AggregationUpdate.update;
import static org.springframework.data.relational.core.sql.StatementBuilder.delete;

/*ACID特性：
Atomicity：原子性
Consistency：一致性
Isolation：隔离性
Durability：持久性
事务可以并发执行，而数据库系统从效率考虑，对事务定义了不同的隔离级别。SQL标准定义了4种隔离级别，分别对应可能出现的数据不一致的情况：
Isolation Level	脏读（Dirty Read）	不可重复读（Non Repeatable Read）	幻读（Phantom Read）
Read Uncommitted	Yes	                    Yes                         	    Yes
Read Committed	    -	                     Yes	                            Yes
Repeatable Read	    -	                    -	                                Yes
Serializable	    -	                    -	                                -       */
public class TransactionL {

//        Connection conn = openConnection();
//        try {
//            // -----关闭自动提交----:
//            conn.setAutoCommit(false);
//            // 执行多条SQL语句:
//            insert(); update(); delete();
//            // ---提交事务---:
//            conn.commit();
//        } catch(SQLException e){
//            // ---回滚事务---:
//            conn.rollback();
//        } finally {
//            conn.setAutoCommit(true);
//            conn.close();
//        }
//        // 设定隔离级别为READ COMMITTED:
//        conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);


}
