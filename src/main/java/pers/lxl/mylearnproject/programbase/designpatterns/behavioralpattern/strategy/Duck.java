package pers.lxl.mylearnproject.programbase.designpatterns.behavioralpattern.strategy;

// public abstract class Duck {
//     public void quack() {
//         System.out.println("叫~");
//     }
//
//     public void swim() {
//         System.out.println("游泳~");
//     }
//
//     public abstract void display();
//
//     public static void main(String[] args) {
//         MallerDuck mallerDuck = new MallerDuck();
//         RedHeadDuck redHeadDuck = new RedHeadDuck();
//         RubberDuck rubberDuck = new RubberDuck();
//         mallerDuck.display();
//         mallerDuck.swim();
//         mallerDuck.quack();
//         redHeadDuck.display();
//         redHeadDuck.swim();
//         redHeadDuck.quack();
//         rubberDuck.display();
//         rubberDuck.swim();
//         rubberDuck.quack();
//     }
// }
//
//
// class MallerDuck extends Duck {
//     @Override
//     public void display() {
//         System.out.println("绿头鸭");
//     }
// }
//
// class RedHeadDuck extends Duck {
//     @Override
//     public void display() {
//         System.out.println("红头鸭");
//     }
// }
//
// class RubberDuck extends Duck {
//     @Override
//     public void display() {
//         System.out.println("橡皮鸭");
//     }
// }

import generics.Performs;

// 现在要让鸭子可以飞？如何做？
// 1.Duck 类中添加Fly()?那么橡皮鸭就也可以飞了？离谱
// 2.橡皮鸭继承后覆盖fly(),让橡皮鸭不能飞？
// 那如果添加木头鸭（不会飞不会叫）、诱饵鸭（不会飞会叫）、、、那么每个都得覆盖？
// 如果鸭子有很多行为？
// 这样会造成：1.很多代码在子类重复2.父类添加一个行为很多子类都得改3.一旦运行时难改变行为4.很难掌控全部行为，添加一个子类得检查所有父类行为
// 3.利用接口？独立出可叫和可飞接口，让可以飞的鸭子实现可飞接口？那么有千类鸭子是否要覆盖千次？而且代码是不可复用的。而且鸭子的飞行动作不一。
// 如果删除或修改一个行为比如可飞接口，那么一千个鸭子都得修改。
// 那应该如何做呢？答案是：---独立出变化的部分---
// 结合---针对接口编程而不是针对实现编程---
// 提出鸭子的飞行和叫的行为成接口，由其对应行为类实现其行为接口，而不是鸭子类实现行为接口。（类似controller使用service，service由serviceImp实现）
// 不要像之前一样由鸭子超类的具体实现或者继承行为接口的子类实现，他们都依赖于实现。
interface FlyBehavior {
    void fly();
}

interface QuackBehavior {
    void quack();
}

// 这些行为接口的实现类与鸭子类无关，所以可以被复用，新增一类也不会影响到其他使用到行为接口的鸭子类
// 在里面添加实例变量，即可得到更加详细的飞行行为
class FlyWithWings implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("用翅膀飞起来啦");
    }
}

class FlyWithBigWings implements FlyBehavior {
    int Wings = 1000;

    @Override
    public void fly() {
        System.out.println("用" + Wings + "米的翅膀飞起来啦");
    }
}

class FlyNoWay implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("完全飞不起来啊");
    }
}

class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("吱吱叫");
    }
}

class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("呱呱叫");
    }
}

class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("不会叫");
    }
}

public class Duck {
    QuackBehavior quackBehavior;
    FlyBehavior flyBehavior;


    public QuackBehavior getQuackBehavior() {
        return quackBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    public FlyBehavior getFlyBehavior() {
        return flyBehavior;
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void performQuack() {
        quackBehavior.quack();
    }

    public void performFly() {
        flyBehavior.fly();
    }

    public void swim() {
    }

    public void display() {
    }

    public static void main(String[] args) {
        Duck duck = new MallardDuck();
        duck.performFly();
        duck.performQuack();
        duck.display();
        // 不通过构造器，运行中动态修改行为
        Duck  WoodDock = new WoodDock();
        WoodDock.performFly();
        WoodDock.performQuack();
        WoodDock.display();
        // 动态修改鸭子行为
        WoodDock.setFlyBehavior(new FlyNoWay());
        WoodDock.setQuackBehavior(new MuteQuack());
        WoodDock.performFly();
        WoodDock.performQuack();
        WoodDock.display();
    }
}

class MallardDuck extends Duck {

    // 通过构造器还是制造一个具体的实现，后面可通过其他方法动态指定
    public MallardDuck() {
        quackBehavior = new Quack();
        flyBehavior = new FlyWithBigWings();
    }


    @Override
    public void display() {
        System.out.println("我是真的绿头鸭");
    }
}

class WoodDock extends Duck {
    public WoodDock() {
        quackBehavior = new Quack();
        flyBehavior = new FlyWithBigWings();
    }
    @Override
    public void display() {
        System.out.println("我是一个木头鸭");
    }
}