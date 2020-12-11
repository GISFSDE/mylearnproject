package pers.lxl.mylearnproject.programbase.designpatterns.creationalpattern.factorymethod.Factory;
/**针对简单工厂，使用工厂设计模式作以下修改*/


/**抽象产品*/
interface Food{
    void eat();
}

/**具体产品*/
class Hamburger implements Food {

    @Override
    public void eat() {
        System.out.println("吃汉堡包！");
    }
}

class RiceNoodle implements Food {

    @Override
    public void eat() {
        System.out.println("过桥米线");
    }
}

/**简单工厂
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
}*/

interface FoodFactory{
    public Food getFood();
}

class HamnurgerFactory implements FoodFactory{

    @Override
    public Food getFood() {
        return new Hamburger();
    }
}

class RiceNoodleFactory implements FoodFactory{

    @Override
    public Food getFood() {
        return new RiceNoodle();
    }
}
/**作者配套框架*/
class Bussiness{
    public static void taste(FoodFactory ff) {
        Food f = ff.getFood();
        System.out.println("评委1，品尝色");
        f.eat();
        Food f2 = ff.getFood();
        System.out.println("评委2，品尝香");
        f2.eat();
        Food f3= ff.getFood();
        System.out.println("评委3，品尝味");
        f3.eat();
    }
}


/**代码用户()
 * 期望是代码作者无论如何修改，代码用户应该不知道，也不用改（迪米特法则）*/
/**代码用户扩展产品*/
class LP implements Food{
    @Override
    public void eat() {
        System.out.println("从小就吃凉皮");
    }
}

class LPFactory implements FoodFactory{
    @Override
    public Food getFood() {
        return new LP();
    }
}

public class FactorySolve {
    public static void main(String[] args) {
        FoodFactory ff = new LPFactory();
//        FoodFactory ff = new RiceNoodleFactory();
        //向上转型调用方法只跟右边有关
        Food food = ff.getFood();
        food.eat();
        Bussiness.taste(ff);
    }
}

/*优点：
1.仍然具有简单工厂的优点，服务器端修改了具体产品的类名以后客户端不知道
2.当客户端需要扩展一个新的产品时，不需要修改作者原来的代码，只是扩展一个新的工厂而已


杠点：
1.简单工厂和工厂方法优点在于服务器端的具体产品类名变化以后客户端不知道，
但是反观代码，客户端仍然依赖于具体的工厂类名啊，此时如果服务器修改了具体工厂的类名，那么客户端也要随之修改？？？

解释：工厂的名字，是视为接口的，作者有责任，有义务保证工厂的名字是稳定的。也就是说虽然客户端依赖于工厂的具体类名，但是IT行业，所有工厂的名字都是趋于稳定的。


2.既然知道产品是我们自己客户端扩展出来的，那为什么不直接自己实例化呢？毕竟这个扩展出来的LP产品自己是作者，为什么还要为自己制作的产品做工厂呢？

解释：因为作者在开发是，不仅仅只会开发一些抽象产品，具体产品，对应工厂，还会配套搭配一些提前做好的框架。

3.现在制作LPFactory，是为了能把LPFactory传入给Bussiness.taste方法，所以必须定义这个LPFactory，那为什么不一开始就让Bussiness.taste方法直接接受Food参数呢？而不是现在的FoodFactory作为参数

解释：改名字耦合了
* */