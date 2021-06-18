package pers.lxl.mylearnproject.programbase.designpatterns.creationalpattern.singletonmethod;

import javax.swing.*;
import java.awt.*;

/**单例模式：饿汉式
 * 热点：饿汉式单例在类创建的同时就已经创建好一个静态的对象供系统使用，以后不再改变，所以是线程安全的，可以直接用于多线程而不会出现问题。*/
public class HungrySingleton {
    private static final HungrySingleton instance = new HungrySingleton();
    private HungrySingleton() {
    }
    public static HungrySingleton getInstance() {
        return instance;
    }
}

//实例

 class SingletonEager {
    public static void main(String[] args) {
        JFrame jf = new JFrame("饿汉单例模式测试");
        jf.setLayout(new GridLayout(1, 2));
        Container contentPane = jf.getContentPane();
        Bajie obj1 = Bajie.getInstance();
        contentPane.add(obj1);
        Bajie obj2 = Bajie.getInstance();
        contentPane.add(obj2);
        if (obj1 == obj2) {
            System.out.println("他们是同一人！");
        } else {
            System.out.println("他们不是同一人！");
        }
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
class Bajie extends JPanel {
    private static Bajie instance = new Bajie();
    private Bajie() {
        JLabel l1 = new JLabel(new ImageIcon("src/Bajie.png"));
        this.add(l1);
    }
    public static Bajie getInstance() {
        return instance;
    }
}