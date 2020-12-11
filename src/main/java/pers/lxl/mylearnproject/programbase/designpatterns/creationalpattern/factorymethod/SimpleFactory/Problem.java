package pers.lxl.mylearnproject.programbase.designpatterns.creationalpattern.factorymethod.SimpleFactory;
/**抽象产品*/
interface Food{
    void eat();
}
/**具体产品*/
class Hamburger implements Food{

    @Override
    public void eat() {
        System.out.println("吃汉堡包！");
    }
}
/**代码用户()
 * 设计脆弱，代码作者修改了产品类名，代码用户也得改，两者耦合
 * 期望是代码作者无论如何修改，代码用户应该不知道，也不用改（迪米特法则）*/
public class Problem {
    public static void main(String[] args) {
        Food f = new Hamburger();
        f.eat();
    }
}
