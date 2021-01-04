package pers.lxl.mylearnproject.javase.thread.lock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**               ----ReentrantLock----
 * 比较
 1. 锁的实现
 synchronized 是 JVM 实现的，而 ReentrantLock 是 JDK 实现的。
 2. 性能
 新版本 Java 对 synchronized 进行了很多优化，例如自旋锁等，synchronized 与 ReentrantLock 大致相同。
 3. 等待可中断
 当持有锁的线程长期不释放锁的时候，正在等待的线程可以选择放弃等待，改为处理其他事情。
 ReentrantLock 可中断，而 synchronized 不行。
 4. 公平锁
 公平锁是指多个线程在等待同一个锁时，必须按照申请锁的时间顺序来依次获得锁。
 synchronized 中的锁是非公平的，ReentrantLock 默认情况下也是非公平的，但是也可以是公平的。
 5. 锁绑定多个条件
 一个 ReentrantLock 可以同时绑定多个 Condition 对象。
 使用选择：除非需要使用 Re entrantLock 的高级功能，否则优先使用 synchronized。这是因为 synchronized 是 JVM 实现的一种锁机制，JVM 原生地支持它，而 ReentrantLock 不是所有的 JDK 版本都支持。并且使用 synchronized 不用担心没有释放锁而导致死锁问题，因为 JVM 会确保锁的释放。**/
public class ConcurrencyLock {
    /** lock方式，不会自动释放对someObject的占用。 lock却必须调用unlock方法进行手动释放，为了保证释放的执行，往往会把unlock() 放在finally中进行。 */
    public static String now() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    public static void log(String msg) {
        System.out.printf("%s %s %s %n", now(), Thread.currentThread().getName(), msg);
    }

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();

        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    log("线程启动");
                    log("试图占有对象：lock");
//lock同步
                    lock.lock();

                    log("占有对象：lock");
                    log("进行5秒的业务操作");
                    Thread.sleep(5000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    log("释放对象：lock");
                    //手动释放
                    lock.unlock();
                }
                log("线程结束");
            }
        };
        t1.setName("t1");
        t1.start();
        try {
            //先让t1飞2秒
            Thread.sleep(2000);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Thread t2 = new Thread() {

            @Override
            public void run() {
                try {
                    log("线程启动");
                    log("试图占有对象：lock");

                    lock.lock();

                    log("占有对象：lock");
                    log("进行5秒的业务操作");
                    Thread.sleep(5000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    log("释放对象：lock");
                    lock.unlock();
                }
                log("线程结束");
            }
        };
        t2.setName("t2");
        t2.start();


        //Lock接口还提供了一个trylock方法。
        //trylock会在指定时间范围内试图占用，占成功了，就啪啪啪。 如果时间到了，还占用不成功，扭头就走~
        //注意： 因为使用trylock有可能成功，有可能失败，所以后面unlock释放锁的时候，需要判断是否占用成功了，如果没占用成功也unlock,就会抛出异常
        //await, signal,signalAll是lock的Condition提供的线程交互方法
        Condition condition = lock.newCondition();
        Thread t3 = new Thread() {

            @Override
            public void run() {
                boolean locked = false;
                try {
                    log("线程启动");
                    log("试图占有对象：lock");

                    locked = lock.tryLock(1, TimeUnit.SECONDS);
                    if (locked) {

                        log("占有对象：lock");
                        log("进行5秒的业务操作");
                        Thread.sleep(5000);
                        condition.signal();
                    } else {
                        log("经过1秒钟的努力，还没有占有对象，放弃占有");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (locked) {
                        log("释放对象：lock");
                        lock.unlock();
                    }
                }
                log("线程结束");
            }
        };
        t3.setName("t3");
        t3.start();
    }

}

