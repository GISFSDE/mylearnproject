package pers.lxl.mylearnproject.programbase.designpatterns.creationalpattern.singletonmethod;
/**基础单例（单线程）隐藏构造函数并实现一个静态的构建方法即可。
 * @author lxl*/
public class SingletonOneThread {
    private static SingletonOneThread instance;
    public String value;
    private SingletonOneThread(String value) {
        // The following code emulates slow initialization.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.value = value;
    }

    public static SingletonOneThread getInstance(String value) {
        if (instance==null){
            instance = new SingletonOneThread(value);
        }
        return instance;
    }
/**----------------客户端程序员*/
    public static void main(String[] args) {
        System.out.println("If you see the same value, then singleton was reused (yay!)" + "\n" +
                "If you see different values, then 2 singletons were created (booo!!)" + "\n\n" +
                "RESULT:" + "\n");
        SingletonOneThread singleton = SingletonOneThread.getInstance("FOO");
        SingletonOneThread anotherSingleton = SingletonOneThread.getInstance("BAR");
        System.out.println(singleton.value);
        System.out.println(anotherSingleton.value);
    }
}
