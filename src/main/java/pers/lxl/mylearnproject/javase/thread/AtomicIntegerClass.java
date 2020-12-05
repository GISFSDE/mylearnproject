package pers.lxl.mylearnproject.javase.thread;

import java.util.concurrent.atomic.AtomicInteger;
/**原子类**/
public class AtomicIntegerClass {
    private static int value = 0;
    private static AtomicInteger atomicValue =new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicI =new AtomicInteger();
        int i = atomicI.decrementAndGet();
        int j = atomicI.incrementAndGet();
        int k = atomicI.addAndGet(4);
        System.out.println(i+" "+j+" "+k);





        int number = 100;
        Thread[] ts1 = new Thread[number];
        for (int iii = 0; iii < number; iii++) {
            Thread t =new Thread(){
                @Override
                public void run(){
                    value++;
                }
            };
            t.start();
            ts1[i] = t;
        }

        //等待这些线程全部结束
        for (Thread t : ts1) {
            try {
                t.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        System.out.printf("%d个线程进行value++后，value的值变成:%d%n", number,value);
        Thread[] ts2 = new Thread[number];
        for (int ii = 0; ii < number; ii++) {
            Thread t =new Thread(){
                @Override
                public void run(){
                    atomicValue.incrementAndGet();
                }
            };
            t.start();
            ts2[ii] = t;
        }

        //等待这些线程全部结束
        for (Thread t : ts2) {
            try {
                t.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.printf("%d个线程进行atomicValue.incrementAndGet();后，atomicValue的值变成:%d%n", number,atomicValue.intValue());
    }
}

