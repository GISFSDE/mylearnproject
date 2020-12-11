package pers.lxl.mylearnproject.programbase.designpatterns.creationalpattern.factorymethod.SimpleFactory;
/**针对问题，使用简单工厂设计模式解决作一下修改*/



/**抽象产品*/
interface Food1{
    void eat();
}

/**具体产品*/
class Hamburger1 implements Food1{

    @Override
    public void eat() {
        System.out.println("吃汉堡包！");
    }
}

class RiceNoodle implements Food1{

    @Override
    public void eat() {
        System.out.println("过桥米线");
    }
}
/**简单工厂*/
class FoodFactory{
    public static Food1 getFood(int n){
        Food1 food=null;
        switch (n){
            case 1:
                food=new Hamburger1();
                break;
            case 2:
                food=new Hamburger1();
                break;
        }
        return food;
    }
}


/**代码用户()
 * 期望是代码作者无论如何修改，代码用户应该不知道，也不用改（迪米特法则）*/
public class Solve {
    public static void main(String[] args) {
//        Food1 f = new Hamburger1();
        Food1 f = FoodFactory.getFood(1);
        f.eat();
    }
}


/*简单工厂
* 优：1.把具体产品类型，从代码用户中解耦
*     2.服务器端如果修改了产品类名，客户端也知道
*
* 缺：1.客户端得死记硬背常量与具体产品映射关系
*     2.具体产品过多会变得十分臃肿
*     3.最重要的是，代码用户需要扩展具体产品的时候，必须修改简单工厂中的代码，违反开闭原则*/