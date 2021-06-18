package pers.lxl.mylearnproject.programbase.designpatterns.creationalpattern.bulider;
/**产品角色（Product）：它是包含多个组成部件的复杂对象，由具体建造者来创建其各个零部件。*/
class Product {
    private String partA;
    private String partB;
    private String partC;
    public void setPartA(String partA) {
        this.partA = partA;
    }
    public void setPartB(String partB) {
        this.partB = partB;
    }
    public void setPartC(String partC) {
        this.partC = partC;
    }
    public void show() {
        System.out.println(partA+' '+partB+' '+partC);
    }
}
/**抽象建造者（Builder）：它是一个包含创建产品各个子部件的抽象方法的接口，通常还包含一个返回复杂产品的方法 getResult()。*/
abstract class Builder {
    //创建产品对象
    protected Product product = new Product();
    public abstract void buildPartA();
    public abstract void buildPartB();
    public abstract void buildPartC();
    //返回产品对象
    public Product getResult() {
        return product;
    }
}
/**具体建造者(Concrete Builder）：实现 Builder 接口，完成复杂产品的各个部件的具体创建方法。*/
class ConcreteBuilder1 extends Builder {
    @Override
    public void buildPartA() {
        product.setPartA("建造 PartA");
    }
    @Override
    public void buildPartB() {
        product.setPartB("建造 PartB");
    }
    @Override
    public void buildPartC() {
        product.setPartC("建造 PartC");
    }
}
/**指挥者（Director）：它调用建造者对象中的部件构造与装配方法完成复杂对象的创建，在指挥者中不涉及具体产品的信息。*/
class Director {
    private Builder builder;
    public Director(Builder builder) {
        this.builder = builder;
    }
    //产品构建与组装方法
    public Product construct() {
        builder.buildPartA();
        builder.buildPartB();
        builder.buildPartC();
        return builder.getResult();
    }
}


public class ConcreteBuilder {
    public static void main(String[] args) {
        Builder builder = new ConcreteBuilder1();
        Director director = new Director(builder);
        Product product = director.construct();
        product.show();
    }
}