package pers.lxl.mylearnproject.javase.thread;

import pers.lxl.mylearnproject.javase.thread.executor.LiftOff;

public class BasicThreads {
    public static void main(String[] args) {
        Thread t=new Thread(new LiftOff());
        t.start();
        System.out.println("Wating for Liftoff");
    }
}
