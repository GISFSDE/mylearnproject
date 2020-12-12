package pers.lxl.mylearnproject.javase.thread;
/**定义任务**/
public class LiftOff implements Runnable {

    protected int countDown = 10;
    private static int taskCount = 0;
    //区分任务的多个实例
    private final int id = taskCount++;

    public LiftOff() {
    }

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + "(" + (countDown > 0 ? countDown : "Liftoff!") + "),";
    }

    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.println(status());
//对静态方法 Thread.yield() 的调用声明了当前线程已经完成了生命周期中最重要的部分，可以切换给其它线程来执行。该方法只是对线程调度器的一个建议，而且也只是建议具有相同优先级的其它线程可以运行。
            Thread.yield();
        }
    }
}
