package pers.lxl.mylearnproject.javase.thread.state;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**一个线程对象只能调用一次start()方法启动新线程，并在新线程中执行run()方法。
一旦run()方法执行完毕，线程就结束了。因此，Java线程的状态有以下几种：

New：新创建的线程，尚未执行；

Runnable：正在 Java 虚拟机中运行。但是在操作系统层面，它可能处于运行状态，也可能等待资源调度（例如处理器资源），资源调度完成就进入运行状态。所以该状态的可运行是指可以被运行，具体有没有运行要看底层操作系统的资源调度。

Blocked：请求获取 monitor lock 从而进入 synchronized 函数或者代码块，但是其它线程已经占用了该 monitor lock，所以出于阻塞状态。要结束该状态进入从而 RUNABLE 需要其他线程释放 monitor lock。

Waiting：等待其它线程显式地唤醒。阻塞和等待的区别在于，阻塞是被动的，它是在等待获取 monitor lock。而等待是主动的，通过调用 Object.wait() 等方法进入。
               进入方法	                                         退出方法
 没有设置 Timeout 参数的 Object.wait() 方法	        Object.notify() / Object.notifyAll()
 没有设置 Timeout 参数的 Thread.join() 方法	        被调用的线程执行完毕
 LockSupport.park() 方法	                        LockSupport.unpark(Thread)

Timed Waiting：无需等待其它线程显式地唤醒，在一定时间之后会被系统自动唤醒
            进入方法	                                    退出方法
        Thread.sleep() 方法	                                时间结束
 设置了 Timeout 参数的 Object.wait() 方法	时间结束 / Object.notify() / Object.notifyAll()
 设置了 Timeout 参数的 Thread.join() 方法	时间结束 / 被调用的线程执行完毕
 LockSupport.parkNanos() 方法	                  LockSupport.unpark(Thread)
 LockSupport.parkUntil() 方法	                    LockSupport.unpark(Thread)

Terminated：线程已终止，因为run()方法执行完毕。
 睡眠和挂起是用来描述行为，而阻塞和等待用来描述状态。
用一个状态转移图表示如下：
                      New
                      │
                     ▼
    │Runnable可运行││ Blocked 阻塞 │
│Waiting无限期等待││Timed Waiting限期等待│
                   │
                   ▼
         │ Terminated 死亡│
         线程终止的原因有：
线程正常终止：run()方法执行到return语句返回；
线程意外终止：run()方法因为未捕获的异常导致线程终止；
对某个线程的Thread实例调用stop()方法强制终止（强烈不推荐使用）。
**/
public class StateL {
    public static void main(String[] args) throws InterruptedException, IOException {

//        BufferedImage bufImage = ImageIO.read(new File("pictures/线程状态转换.jpg"));
        ExecutorService executorService = Executors.newCachedThreadPool();
        StateL example = new StateL();
        executorService.execute(() -> example.after());
        executorService.execute(() -> example.before());
        executorService.execute(() -> example.after1());
        executorService.execute(() -> example.before1());


        Thread t = new Thread(() -> {
            System.out.println("hello");
        });
        System.out.println("start");
        t.start();
        //一个线程还可以等待另一个线程直到其运行结束。例如，main线程在启动t线程后，可以通过t.join()等待t线程结束后再继续运行：
        t.join(100);
        //t.join(100);的重载方法也可以指定一个等待时间，超过等待时间后就不再继续等待。
        System.out.println("end");
    }

    public synchronized void before() {
        System.out.println("before");
        notifyAll();
    }
/**调用 wait() 使得线程等待某个条件满足，线程在等待时会被挂起，当其他线程的运行使得这个条件满足时，其它线程会调用 notify() 或者 notifyAll() 来唤醒挂起的线程。
 它们都属于 Object 的一部分，而不属于 Thread。
 只能用在同步方法或者同步控制块中使用，否则会在运行时抛出 IllegalMonitorStateException。
 使用 wait() 挂起期间，线程会释放锁。这是因为，如果没有释放锁，那么其它线程就无法进入对象的同步方法或者同步控制块中，那么就无法执行 notify() 或者 notifyAll() 来唤醒挂起的线程，造成死锁。*/
/**---wait() 和 sleep() 的区别----
 wait() 是 Object 的方法，而 sleep() 是 Thread 的静态方法；
 wait() 会释放锁，sleep() 不会。*/
    public synchronized void after() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after");
    }

/**    java.util.concurrent 类库中提供了 Condition 类来实现线程之间的协调，
 可以在 Condition 上调用 await() 方法使线程等待，其它线程调用 signal() 或 signalAll() 方法唤醒等待的线程。
相比于 wait() 这种等待方式，await() 可以指定等待的条件，因此更加灵活。

 使用lock来获取一个Condition对象*/
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void before1() {
        lock.lock();
        try {
            System.out.println("before1");
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void after1() {
        lock.lock();
        try {
            condition.await();
            System.out.println("after1");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
