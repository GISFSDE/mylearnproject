package pers.lxl.mylearnproject.javase.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**如果多个线程同时读写共享变量，会出现数据不一致的问题。
 * Java 提供了两种锁机制来控制多个线程对共享资源的互斥访问，
 * 第一个是 JVM 实现的 synchronized，而另一个是 JDK 实现的 ReentrantLock
 * @author lxl**/
public class ThreadSynchronization {
/**同步一个代码块（方法public synchronized void func () {}）---它只作用于同一个对象，如果调用两个对象上的同步代码块，就不会进行同步
对于以下代码，使用 ExecutorService 执行了两个线程，
 由于调用的是同一个对象的同步代码块，因此这两个线程会进行同步，
 当一个线程进入同步语句块时，另一个线程就必须等待。*/
    public void func1() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
//            System.out.println("---------------------");为何加了这一句就分开了？
        }
    }

    /**同步一个类---两个线程调用同一个类的不同对象上的这种同步语句，也会进行同步*/
    public void func2() {
        synchronized (ThreadSynchronization.class) {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
        }
    }
    /**同步一个静态方法public synchronized static void fun() {}---作用于整个类。*/


    public static void main(String[] args) {
        ThreadSynchronization e1 = new ThreadSynchronization();
        ThreadSynchronization e2 = new ThreadSynchronization();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> e1.func1());
//        executorService.execute(() -> e1.func1());
//        两个线程调用了不同对象的同步代码块，因此这两个线程就不需要同步。从输出结果可以看出，两个线程交叉执行。
        executorService.execute(() -> e2.func1());


//        executorService.execute(() -> e1.func2());
//        executorService.execute(() -> e2.func2());
    }
}
