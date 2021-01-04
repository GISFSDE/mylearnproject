package pers.lxl.mylearnproject.javase.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**                  ----Executor----
 * //简单的创建多线程方式，创建一个线程（和后续的销毁）开销是非常昂贵的，
 * // 因为 JVM 和操作系统都需要分配资源。而且创建的线程数也是不可控的，
 * // 这将可能导致系统资源被迅速耗尽。为了能更好的控制多线程，
 * // JDK 提供了一套 Executor 框架，其本质就是一个线程池
 * 管理多个异步任务的执行，而无需程序员显式地管理线程的生命周期。
 * 这里的异步是指多个任务的执行互不干扰，不需要进行同步操作。
 * Executor 框架提供了各种类型的线程池, 主要有以下工厂方法:
 *
 * CachedThreadPool：一个任务创建一个线程；创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程，但是在以前构造的线程可用时将重用它们。对于执行很多短期异步任务的程序而言，这些线程池通常可提高程序性能。
 * FixedThreadPool：所有任务只能使用固定大小的线程；创建一个可重用固定线程数的线程池，以共享的无界队列方式来运行这些线程，超出的线程会在队列中等待。
 * SingleThreadExecutor：相当于大小为 1 的 FixedThreadPool。创建一个使用单个 worker 线程的 Executor，以无界队列方式来运行该线程，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序 (FIFO, LIFO, 优先级) 执行。
 *
 *  public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize)
 * 创建一个定长线程池，支持定时及周期性任务执行。前三个方法都可以配合接口 ThreadFactory 的实例一起使用。并且返回一个 ExecutorService 接口的实例
 * https://blog.csdn.net/xuemengrui12/article/details/78543543
 * @author lxl*/
public class CatchThreadPool {
    public static void main(String[] args) {
//        Executors.newFixedThreadPool(4);有限线程集
//        Executors.newSingleThreadExecutor();线程数量为1的newFixedThreadPool(),如果对其提交多个任务，将会排队执行
        ExecutorService exec= Executors.newCachedThreadPool();
        for (int i = 0; i <5 ; i++) {
            exec.execute(new LiftOff());
            //防止新任务被提交给这个Executer
            //shutdown() 方法会等待线程都执行完毕之后再关闭，但是如果调用的是 shutdownNow() 方法，则相当于调用每个线程的 interrupt() 方法
            // 如果只想中断 Executor 中的一个线程，可以通过使用 submit() 方法来提交一个线程，它会返回一个 Future<?> 对象，通过调用该对象的 cancel(true) 方法就可以中断线程。
            exec.shutdown();
        }
    }
}
