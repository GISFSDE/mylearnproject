package pers.lxl.mylearnproject.programbase.nosql.Redis;


import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Redis学习
 * 首先需手动启动Redis服务redis目录下开启 cmd： .\redis-server.exe redis.windows.conf
 * 不关闭上一个再开启cmd： .\redis-cli.exe -h 127.0.0.1 -p 6379连接
 * 数据类型：String，List，Set，ZSet（有序），Hash，
 */
public class RedisLearn {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        // 如果 Redis 服务设置来密码，需要下面这行，没有就不需要
        // jedis.auth("123456");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: " + jedis.ping());
//String(字符串)
        jedis.set("foo", "bar");
        String value = jedis.get("foo");
        System.out.println("value=" + value);
        jedis.del("foo");
        System.out.println("value=" + jedis.get("foo"));
//List(列表)
        // 存储数据到列表
        jedis.lpush("tutorial-list", "Redis");
        jedis.lpush("tutorial-list", "Mongodb");
        jedis.lpush("tutorial-list", "Mysql");
        jedis.rpush("tutorial-list", "Memcached");
        // 输出列表数据
        List<String> list = jedis.lrange("tutorial-list", 0, 1000);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Stored string in redis:: " + list.get(i));
        }
//Set(集合)
        // 将给定元素添加到集合
        jedis.sadd("setTest", "a");
        jedis.sadd("setTest", "b");
        jedis.sadd("setTest", "a");

        // 检查给定元素是否存在于集合中
        System.out.println("a is exist in setTest ?" + jedis.sismember("setTest", "a"));

        // 返回集合包含的所有元素
        Set set = jedis.smembers("setTest");
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // 如果给定的元素存在于集合中，那么移除这个元素
        jedis.srem("setTest", "a");
        Set finalSet = jedis.smembers("setTest");
        Iterator iterator1 = finalSet.iterator();
        System.out.println("删除后集合中元素...");
        while (iterator1.hasNext()) {
            System.out.println(iterator1.next());
        }
//Hash(散列)
        // 设置键值对
        jedis.hset("hashTest", "a", "b");
        jedis.hset("hashTest", "b", "c");
        jedis.hset("hashTest", "a", "b");

        // 获取 hash 中的所有键值对
        Map map = jedis.hgetAll("hashTest");
        // 根据 key 获取 value
        System.out.println(map.get("a"));
        System.out.println(map.get("b"));
        System.out.println(map.get("c"));

        System.out.println("删除后...");

        jedis.hdel("hashTest", "a");
        Map map1 = jedis.hgetAll("hashTest");
        System.out.println(map1.get("a"));
        System.out.println(map1.get("b"));
        System.out.println(map1.get("c"));
//ZSET(有序集合)
        // 将一个带有给定分值的成员添加到有序集合里面
        jedis.zadd("zsetTest", 10, "a");
        jedis.zadd("zsetTest", 20, "b");

        // 获取有序集合在给定分值范围内的所有元素
        Set zset = jedis.zrangeByScore("zsetTest", 5, 10);
        Iterator iterator2 = zset.iterator();
        while (iterator2.hasNext()) {
            System.out.println(iterator2.next());
        }
        System.out.println("........");
        //     根据分值的排序顺序，获取有序集合在给定位置范围内的所有元素
        Set zset1 = jedis.zrange("zsetTest", 0, -1);
        Iterator iterator3 = zset1.iterator();
        while (iterator3.hasNext()) {
            System.out.println(iterator3.next());
        }

    }
}
