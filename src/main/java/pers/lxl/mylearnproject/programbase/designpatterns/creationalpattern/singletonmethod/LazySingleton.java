package pers.lxl.mylearnproject.programbase.designpatterns.creationalpattern.singletonmethod;
/**单例模式：懒汉式
 * 特点:类加载时没有生成单例,只有当第一次调用 getlnstance 方法时才去创建这个单例。
 * 缺点：如果编写的是多线程程序，则不要删除上例代码中的关键字 volatile 和 synchronized(双重静态锁)，否则将存在线程非安全的问题。如果不删除这两个关键字就能保证线程安全，但是每次访问时都要同步，会影响性能，且消耗更多的资源，这是懒汉式单例的缺点。*/
public class LazySingleton {
    private static volatile LazySingleton instance = null;    //保证 instance 在所有线程中同步
    private LazySingleton() {
    }    //private 避免类在外部被实例化
    public static synchronized LazySingleton getInstance() {
        //getInstance 方法前加同步
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}


//实例
class SingletonLazy {
    public static void main(String[] args) {
        President zt1 = President.getInstance();
        zt1.getName();    //输出总统的名字
        President zt2 = President.getInstance();
        zt2.getName();    //输出总统的名字
        if (zt1 == zt2) {
            System.out.println("他们是同一人！");
        } else {
            System.out.println("他们不是同一人！");
        }
    }
}
class President {
    private static volatile President instance = null;    //保证instance在所有线程中同步
    //private避免类在外部被实例化
    private President() {
        System.out.println("产生一个总统！");
    }
    public static synchronized President getInstance() {
        //在getInstance方法上加同步
        if (instance == null) {
            instance = new President();
        } else {
            System.out.println("已经有一个总统，不能产生新总统！");
        }
        return instance;
    }
    public void getName() {
        System.out.println("我是美国总统：特朗普。");
    }
}