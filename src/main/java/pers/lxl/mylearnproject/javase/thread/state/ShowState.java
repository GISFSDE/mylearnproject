package pers.lxl.mylearnproject.javase.thread.state;

import org.yaml.snakeyaml.error.Mark;

import static java.lang.Thread.yield;

/**
 * 线程状态装换
 * https://gitee.com/mychimei/picture/raw/master/image-20220305200131513.png
 * stateThread.getState()获取线程状态
 */
public class ShowState {
    public static void main(String[] args) throws InterruptedException {
        final long ONE_SECONDS = 1000;
        // --> NEW
        Thread mark = new Thread(() -> {
            synchronized (args) {
                try {
                    // --> BLOCKED
                    blocking(ONE_SECONDS);

//                    yield();
                    // --> WAITING
                    args.wait();
                    // --> TIMED_WAITING
                    args.wait(ONE_SECONDS);
                } catch (InterruptedException e) {
                }
            }
        });
        printThreadState(mark);
        // 执行使 mark 线程启动后进入监视器锁定阻塞的线程（等待获取锁）
        new Thread(() -> {
            try {
                blocking(ONE_SECONDS + 500);
            } catch (InterruptedException e) {
            }
        }).start();
        Thread.sleep(ONE_SECONDS / 10);
        // 启动 mark 线程 --> RUNNABLE
        mark.start();
        // 打印 mark 线程 start() 后的状态
        printThreadState(mark);
        Thread.sleep(ONE_SECONDS / 10);
        // 打印 mark 线程 等待获取 sync 的状态
        printThreadState(mark);
        // 等待 mark 线程 blocking() 执行完成后执行 wait()
        Thread.sleep(ONE_SECONDS * 2 + 500);
        // 打印 mark 线程 wait() 时的状态
        printThreadState(mark);
        synchronized (args) {
            args.notify(); // 唤醒 mark 线程
        }
        // 等待 mark 线程执行 wait(long timeout)
        Thread.sleep(ONE_SECONDS / 2);
        // 获取 mark 线程 wait(long timeout) 时的状态
        printThreadState(mark);
        // 等待 mark 线程执行完毕
        Thread.sleep(ONE_SECONDS);
        // 打印已执行完成的状态 --> TERMINATED
        printThreadState(mark);
    }

    static synchronized void blocking(long timeout) throws InterruptedException {
        Thread.sleep(timeout);
    }

    static void printThreadState(Thread stateThread) {
        System.out.println(stateThread.getState());
    }
}
