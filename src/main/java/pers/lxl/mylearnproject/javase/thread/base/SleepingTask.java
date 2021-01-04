package pers.lxl.mylearnproject.javase.thread.base;

import pers.lxl.mylearnproject.javase.thread.executor.LiftOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SleepingTask extends LiftOff {
    @Override
    public void run() {
        while (countDown-->0){
            try {
                System.out.println(status());
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                System.err.println("Interupted");
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService exec= Executors.newCachedThreadPool();
        for (int i = 0; i <5 ; i++) {
            exec.execute(new SleepingTask());
            exec.shutdown();

        }
    }
}
