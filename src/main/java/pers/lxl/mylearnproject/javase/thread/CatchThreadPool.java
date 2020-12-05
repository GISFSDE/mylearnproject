package pers.lxl.mylearnproject.javase.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CatchThreadPool {
    public static void main(String[] args) {
//        Executors.newFixedThreadPool(4);有限线程集
//        Executors.newSingleThreadExecutor();线程数量为1的newFixedThreadPool(),如果对其提交多个任务，将会排队执行
        ExecutorService exec= Executors.newCachedThreadPool();
        for (int i = 0; i <5 ; i++) {
            exec.execute(new LiftOff());
            //防止新任务被提交给这个Executer
            exec.shutdown();
        }
    }
}
