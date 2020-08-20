package pers.lxl.mylearnproject.javase.oop;
//inherit,复用代码,子类无法访问父类私有字段方法，可以访问父类protected字段方法
public class Student extends Person{
private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
