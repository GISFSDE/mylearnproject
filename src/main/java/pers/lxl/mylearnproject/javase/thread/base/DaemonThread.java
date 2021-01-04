package pers.lxl.mylearnproject.javase.thread.base;

import pers.lxl.mylearnproject.javase.thread.interrupt.MyThread;

/**守护线程（后台线程）
Java程序入口就是由JVM启动main线程，main线程又可以启动其他线程。
当所有线程都运行结束时，JVM退出，进程结束。
如果有一个线程没有退出，JVM进程就不会退出。
有一种线程的目的就是无限循环,
守护线程是指为其他线程服务的线程。在JVM中，所有非守护线程都执行完毕后，
无论有没有守护线程，虚拟机都会自动退出。
因此，JVM退出时，不必关心守护线程是否已结束。
 * @author lxl**/
public class DaemonThread {
    public static void main(String[] args) {
        Thread t = new MyThread();
        //调用start()方法前，调用setDaemon(true)把该线程标记为守护线程
        t.setDaemon(true);
        //守护线程不能持有任何需要关闭的资源，例如打开文件等，因为虚拟机退出时，守护线程没有任何机会来关闭文件，这会导致数据丢失。
        t.start();
        System.out.println(t.isDaemon());
    }
}
