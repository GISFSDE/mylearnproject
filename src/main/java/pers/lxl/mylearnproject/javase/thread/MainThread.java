package pers.lxl.mylearnproject.javase.thread;

import pers.lxl.mylearnproject.javase.thread.executor.LiftOff;

public class MainThread {
    public static void main(String[] args) {
        LiftOff launch = new LiftOff();
        launch.run();
    }
}
