package pers.lxl.mylearnproject.javase.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 快速交替执行看起来像是同时执行
 * 进程和线程的关系：一个进程可以包含一个或多个线程，但至少会有一个线程
 * 多进程模式（每个进程只有一个线程）
 * 多线程模式（一个进程有多个线程）
 * 多进程＋多线程模式（复杂度最高）
 * 和多线程相比，多进程的缺点在于：
 * 创建进程比创建线程开销大，尤其是在Windows系统上；
 * 进程间通信比线程间通信要慢，因为线程间通信就是读写同一个变量，速度很快。
 * 多进程的优点在于：
 * 多进程稳定性比多线程高，因为在多进程的情况下，一个进程崩溃不会影响其他进程，而在多线程的情况下，任何一个线程崩溃会直接导致整个进程崩溃。
 * Java语言内置了多线程支持：一个Java程序实际上是一个JVM进程，JVM进程用一个主线程来执行main()方法，在main()方法内部，我们又可以启动多个线程。此外，JVM还有负责垃圾回收的其他工作线程等。
 * 因此，对于大多数Java程序来说，我们说多任务，实际上是说如何使用多线程实现多任务。
 * 和单线程相比，多线程编程的特点在于：多线程经常需要读写共享数据，并且需要同步。例如，播放电影时，就必须由一个线程播放视频，另一个线程播放音频，两个线程需要协调运行，否则画面和声音就不同步。因此，多线程编程的复杂度高，调试更困难。
 * Java多线程编程的特点又在于：
 * 多线程模型是Java程序最基本的并发模型；
 * 后续读写网络、数据库、Web开发等都依赖Java多线程模型。
 *
 * @author lxl
 **/
public class HelloClass {

    //希望新线程能执行指定的代码，有以下几种方法：

    /**
     * 方法一：从Thread派生一个自定义类，然后覆写run()方法，启动：子类对象.start(),为避免OOP单继承局限性，不建议使用
     */
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("start new thread!extends Thread");
        }
    }

    /**
     * 方法二：创建Thread实例时，传入一个Runnable实例：启动：传入目标对象+Thread对象.start()，建议使用:避免单继承局限性，灵活方便同一个对象被多个线程使用
     */
    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("start new thread!implements Runnable");

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
            System.out.println("thread end.");
        }
    }

    /**
     * 方法三：实现Callable接口,与Runnable相比,Callable可以有返回值,返回值通过FutureTask进行封装（很少用）
     */
    static class MyCallable implements Callable<Integer> {
        @Override
        public Integer call() {
            return 123;
        }
    }
//相比之下,接口可以实现多个,而Thread只能单继承,
// 继承整个Threa类开销过大,所以实现接口方式更好一些


    /**
     * 当Java程序启动的时候，实际上是启动了一个JVM进程，然后，JVM启动主线程来执行main()方法。在main()方法中，我们又可以启动其他线程。
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建新线程
        System.out.println("main start...");
        //Thread thread = new Thread();//不能执行指定代码


//extends Thread
        // Thread thread = new MyThread();

//implements Callable
        MyCallable mc = new MyCallable();
//        FutureTask封装Callable 返回值
        FutureTask<Integer> ft = new FutureTask<>(mc);
        Thread thread1 = new Thread(ft);
        thread1.start();
        System.out.println(ft.get());


//implements Runnable
        Thread thread = new Thread(new MyRunnable());
        //Thread thread = new Thread(() -> {System.out.println("start new thread!"); });//Java8引入的lambda写法
        thread.start();// 启动新线程，直接调用Thread实例的run()方法是无效的，线程开启不一定立即执行，由CPU调度决定


//        Thread.setPriority(int n) // 1~10, 默认值5
        try {
            Thread.sleep(20);
//            sleep() 可能会抛出 InterruptedException，因为异常不能跨线程传播回 main() 中，因此必须在本地进行处理。线程中抛出的其它异常也同样需要在本地进行处理。
        } catch (InterruptedException e) {
        }
        System.out.println("main end...");
    }


}
