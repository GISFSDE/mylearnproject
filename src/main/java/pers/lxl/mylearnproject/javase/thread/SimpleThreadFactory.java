package pers.lxl.mylearnproject.javase.thread;

import pers.lxl.mylearnproject.javase.thread.interrupt.MyThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class SimpleThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r);
    }
}
/** 设置后台线程属性
 */
class DaemonThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    }
}
/** 设置优先级属性
 *最高优先级
 */
class MaxPriorityThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setPriority(Thread.MAX_PRIORITY);
        return t;
    }
}
/**
 * 启动带有属性设置的线程
 */
class ExecFromFactory {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();

        ExecutorService defaultExec = Executors
                .newCachedThreadPool(Executors.defaultThreadFactory());
        ExecutorService daemonExec = Executors
                .newCachedThreadPool(new DaemonThreadFactory());
        ExecutorService maxPriorityExec = Executors
                .newCachedThreadPool(new MaxPriorityThreadFactory());

        for (int i = 0; i < 6; i++) {
            if (i == 1) {
                defaultExec.execute(new MyThread());
            } else if (i == 3) {
                daemonExec.execute(new MyThread());
            } else if (i == 5) {
                maxPriorityExec.execute(new MyThread());
            } else {
                executorService.execute(new MyThread());
            }
        }
    }
}