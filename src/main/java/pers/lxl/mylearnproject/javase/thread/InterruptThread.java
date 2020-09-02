package pers.lxl.mylearnproject.javase.thread;
/*如果线程需要执行一个长时间任务，就可能需要能中断线程。
中断线程就是其他线程给该线程发一个信号，该线程收到信号后结束执行run()方法，
使得自身线程能立刻结束运行。
interrupt()目标线程需要反复检测自身状态是否是interrupted状态，如果是，就立刻结束运行*/
//--中断线程---
public class InterruptThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t=new MyThread();
        t.start();
        Thread.sleep(100);
        t.interrupt();//方法1----interrupt()方法仅仅向t线程发出了“中断请求”，至于t线程是否能立刻响应，要看具体代码。
        t.join();
        System.out.println("end");

        HelloThread1 t1 = new HelloThread1();
        t1.start();
        Thread.sleep(1);
        t1.running = false; // 方法1----标志位置为false
    }

}
class MyThread extends Thread{
    @Override
    public void run() {
        Thread hello = new HelloThread();
        hello.start(); // 启动hello线程
        try {
            hello.join(); // 等待hello线程结束
        } catch (InterruptedException e) {
            System.out.println("interrupted!");
        }
        hello.interrupt();
    }
}
class HelloThread extends Thread {
    public void run() {
        int n = 0;
        while (!isInterrupted()) {
            n++;
            System.out.println(n + " hello!");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}

class HelloThread1 extends Thread {
    public volatile boolean running = true;//线程间共享的变量,使用volatile关键字标记，确保每个线程都能读取到更新后的变量值。
    //volatile关键字的目的是告诉虚拟机：
    //每次访问变量时，总是获取主内存的最新值；
    //每次修改变量后，立刻回写到主内存。
    //解决的是可见性问题：当一个线程修改了某个共享变量的值，其他线程能够立刻看到修改后的值。
    //如果我们去掉volatile关键字，运行上述程序，发现效果和带volatile差不多，这是因为在x86的架构下，JVM回写主内存的速度非常快，但是，换成ARM的架构，就会有显著的延迟。
    public void run() {
        int n = 0;
        while (running) {
            n ++;
            System.out.println(n + " hello!");
        }
        System.out.println("end!");
    }
}
