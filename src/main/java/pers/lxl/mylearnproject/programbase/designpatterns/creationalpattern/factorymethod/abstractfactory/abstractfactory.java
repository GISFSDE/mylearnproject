package pers.lxl.mylearnproject.programbase.designpatterns.creationalpattern.factorymethod.abstractfactory;

public class abstractfactory {
    public static void main(String[] args) {
        Creator creator = new CreatorImpl();
        Product product = creator.createCar();
        product.detail();
        product = creator.createPlane();
        product.detail();
        product.common();
    }
}
abstract class Product {
    /**
     * 抽象产品
     */
    public void common() {
        System.out.println("公共方法");
    }

    public abstract void detail();
}
class Car extends Product {
    @Override
    public void detail() {
        System.out.println("汽车");
    }
}
class Plane extends Product {

    @Override
    public void detail() {
        System.out.println("飞机");
    }
}
/**抽象工厂*/
interface Creator {
    Product createCar();

    Product createPlane();

}
class CreatorImpl implements Creator {
    @Override
    public Product createCar() {
        return new Car();
    }

    @Override
    public Product createPlane() {
        return new Plane();
    }
}