package pers.lxl.mylearnproject.javase.thread.pool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * @author Administrator
 */
public class ThreadPoolTest {
    /**使用Executors创建线程池，建议手动创建线程池
     * 线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。 说明：Executors返回的线程池对象的弊端如下：
     * 1）FixedThreadPool和SingleThreadPool:
     * 允许的请求队列长度为Integer.MAX_VALUE，可能会堆积大量的请求，从而导致OOM。
     * 2）CachedThreadPool:
     * 允许的创建线程数量为Integer.MAX_VALUE，可能会创建大量的线程，从而导致OOM。*/
    @Test
    public void createPoolByExecutors() {
        //一池五线程 5个窗口
        ExecutorService threadPool1 = Executors.newFixedThreadPool(5);

        //一池一线程 一个窗口
        ExecutorService threadPool2 = Executors.newSingleThreadExecutor();

        //一池可扩容线程
        ExecutorService threadPool3 = Executors.newCachedThreadPool();
        //
        ExecutorService threadPool4 = Executors.newScheduledThreadPool(1);
        //10个顾客请求
        try {
            for (int i = 1; i <= 10; i++) {
                //执行
                threadPool2.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭
            threadPool2.shutdown();
        }

    }
    /**手动创建线程池*/
    @Test
    public void createPoolByThreadPoolExecutor() {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        //10个顾客请求
        try {
            for (int i = 1; i <= 10; i++) {
                //执行
                threadPool.execute(() -> {
//                    Thread.currentThread()访问当前线程
                    System.out.println(Thread.currentThread().getName() + " 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭
            threadPool.shutdown();
        }
    }
    public static void main(String[] args) {
// ThreadPoolExecutor threadPool = new ThreadPoolExecutor(corePoolSize：10, maximumPoolSize：15, keepAliveTime：6060, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
//Java自带线程池
//第一个参数corePoolSize：10 表示这个线程池初始化了10个线程在里面工作
//第二个参数maximumPoolSize：15 表示如果10个线程不够用了，就会自动增加到最多15个线程
//第三个参数keepAliveTime：60 结合第四个参数TimeUnit.SECONDS，表示经过60秒，多出来的线程还没有接到活儿，就会回收，最后保持池子里就10个
//第四个参数TimeUnit.SECONDS 如上
//第五个参数 new LinkedBlockingQueue() 用来放任务的集合
//execute方法用于添加新的任务
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 15, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                System.out.println("任务1");
            }
        });


        ThreadPool pool = new ThreadPool();

        for (int i = 0; i < 5; i++) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    //System.out.println("执行任务");
                    //任务可能是打印一句话
                    //可能是访问文件
                    //可能是做排序
                }
            };

            pool.add(task);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}
