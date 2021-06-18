package pers.lxl.mylearnproject.programbase.designpatterns.creationalpattern.factorymethod.SimpleFactory;

public class SimpleFactory {
    public static void main(String[] args) {
        Product c= SimpleFactory1.makeProduct(1);
        c.show();
    }
    //抽象产品
    public interface Product {
        void show();
    }
    //具体产品：ProductA
    static class ConcreteProduct1 implements Product {
        @Override
        public void show() {
            System.out.println("具体产品1显示...");
        }
    }
    //具体产品：ProductB
    static class ConcreteProduct2 implements Product {
        @Override
        public void show() {
            System.out.println("具体产品2显示...");
        }
    }
    static final class Const {
        static final int PRODUCT_A = 0;
        static final int PRODUCT_B = 1;
    }
    static class SimpleFactory1{
        public static Product makeProduct(int kind) {
            switch (kind) {
                case Const.PRODUCT_A:
                    return new ConcreteProduct1();
                case Const.PRODUCT_B:
                    return new ConcreteProduct2();
            }
            return null;
        }
    }
}