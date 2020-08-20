package pers.lxl.mylearnproject.javase.oop;

//inherit,复用代码,子类无法访问父类私有字段方法，可以访问父类protected字段方法
public class Student extends Person {
    protected Book book;//继承是is关系，组合是has关系
    private int score;
    //Override
    @Override//让编译器帮助检查是否进行了正确的覆写。希望进行覆写，但是不小心写错了方法签名，编译器会报错。
    public void run(){
        System.out.println("Student run");
    }
//    public int run(){
//        System.out.println("Student run");
//        return age;
//    }
    public void run(String name){
        System.out.println("Student run");
    }

    public Student(String name, int age, int score) {
        //super();//任何class的构造方法，第一行语句必须是调用父类的构造方法,没有编译器自动添加，父类如果没有默认会报编译错误
        super(name, age);
        this.score = score;
    }
    public Student() {
        this.score = score;
    }
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
