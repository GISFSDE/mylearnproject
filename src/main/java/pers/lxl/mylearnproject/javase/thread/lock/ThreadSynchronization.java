package pers.lxl.mylearnproject.javase.thread.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**     -----synchronized-----
 * 如果多个线程同时读写共享变量，会出现数据不一致的问题。
 * Java 提供了两种锁机制来控制多个线程对共享资源的互斥访问，
 * 第一个是 JVM 实现的 synchronized，而另一个是 JDK 实现的 ReentrantLock
 *
 * @author lxl
 **/
public class ThreadSynchronization {
    /**
     * 同步一个代码块（方法public synchronized void func () {}）---它只作用于同一个对象，如果调用两个对象上的同步代码块，就不会进行同步
     * 对于以下代码，使用 ExecutorService 执行了两个线程，
     * 由于调用的是同一个对象的同步代码块，因此这两个线程会进行同步，
     * 当一个线程进入同步语句块时，另一个线程就必须等待。
     */
    public void func1() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
            //为何加了这一句就分开了？
//            System.out.println("---------------------");
        }
    }

    /**
     * 同步一个方法,和同步代码块一样，作用于同一个对象。
     */
    public synchronized void func() {
    }

    /**
     * 同步一个类---两个线程调用同一个类的不同对象上的这种同步语句，也会进行同步
     */
    public void func2() {
        synchronized (ThreadSynchronization.class) {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
        }
    }

    /**
     * 同步一个静态方法--作用于整个类。
     */
    public synchronized static void fun() {
        // ...
    }

    public static void main(String[] args) {
        ThreadSynchronization e1 = new ThreadSynchronization();
        ThreadSynchronization e2 = new ThreadSynchronization();

//Executor 框架提供了各种类型的线程池, 主要有以下工厂方法:
// * CachedThreadPool：一个任务创建一个线程；创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程，但是在以前构造的线程可用时将重用它们。对于执行很多短期异步任务的程序而言，这些线程池通常可提高程序性能。
// * FixedThreadPool：所有任务只能使用固定大小的线程；创建一个可重用固定线程数的线程池，以共享的无界队列方式来运行这些线程，超出的线程会在队列中等待。
// * SingleThreadExecutor：相当于大小为 1 的 FixedThreadPool。创建一个使用单个 worker 线程的 Executor，以无界队列方式来运行该线程，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序 (FIFO, LIFO, 优先级) 执行。
//       见阿里巴巴手册，不建议使用Executors创建线程池，容易导致OOM
        ExecutorService executorService = Executors.newCachedThreadPool();

        //同步代码块
        executorService.execute(() -> e1.func1());
        //调用相同对象同步代码块，线程同步
        //executorService.execute(() -> e1.func1());
        //两个线程调用了不同对象的同步代码块，因此这两个线程就不需要同步。从输出结果可以看出，两个线程交叉执行。
        executorService.execute(() -> e2.func1());

        //同步一个类，作用于整个类，两个线程调用同类不同对象会进行同步。
        executorService.execute(() -> e1.func2());
        executorService.execute(() -> e2.func2());
    }
}
