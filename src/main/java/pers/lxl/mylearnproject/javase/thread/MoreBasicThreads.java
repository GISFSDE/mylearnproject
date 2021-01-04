package pers.lxl.mylearnproject.javase.thread;

import pers.lxl.mylearnproject.javase.thread.executor.LiftOff;

public class MoreBasicThreads {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new LiftOff()).start();
            System.out.println("Wating for Liftoff!");
        }
    }
}
