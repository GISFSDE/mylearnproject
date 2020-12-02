package pers.lxl.mylearnproject.javase.thread;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**一个线程对象只能调用一次start()方法启动新线程，并在新线程中执行run()方法。
一旦run()方法执行完毕，线程就结束了。因此，Java线程的状态有以下几种：
New：新创建的线程，尚未执行；
Runnable：运行中的线程，正在执行run()方法的Java代码；
Blocked：运行中的线程，因为某些操作被阻塞而挂起；
Waiting：运行中的线程，因为某些操作在等待中；
Timed Waiting：运行中的线程，因为执行sleep()方法正在计时等待；
Terminated：线程已终止，因为run()方法执行完毕。
用一个状态转移图表示如下：
                New
                │
                ▼
│  Runnable  │ │   Blocked   │
│   Waiting  │ │Timed Waiting│
                │
                ▼
         │ Terminated │
         线程终止的原因有：
线程正常终止：run()方法执行到return语句返回；
线程意外终止：run()方法因为未捕获的异常导致线程终止；
对某个线程的Thread实例调用stop()方法强制终止（强烈不推荐使用）。
**/
public class StateL {
    public static void main(String[] args) throws InterruptedException, IOException {
        //线程状态图，只提供查看
//        BufferedImage bufImage = ImageIO.read(new File("F:\\PROJECT\\IDEA\\mylearnproject\\src\\main\\resources\\pictures\\线程状态转换.jpg"));
        BufferedImage bufImage = ImageIO.read(new File("pictures/线程状态转换.jpg"));



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
}
