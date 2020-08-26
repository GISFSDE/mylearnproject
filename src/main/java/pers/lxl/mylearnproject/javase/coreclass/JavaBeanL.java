package pers.lxl.mylearnproject.javase.coreclass;
/*JavaBean主要用来传递数据，即把一组数据组合成一个JavaBean便于传输。
此外，JavaBean可以方便地被IDE工具分析，生成读写属性的代码，
主要用在图形界面的可视化设计中。
IDEA alt+insert添加相关方法，或者相关插件，但是会影响可读性
要枚举一个JavaBean的所有属性，可以直接使用Java核心库提供的Introspector：*/
public class JavaBeanL {
    private String name;
    private int num;
    boolean flog;

    public boolean isFlog() {
        return flog;
    }

    public void setFlog(boolean flog) {
        this.flog = flog;
    }


    public void setName(String name) {//write-only
        this.name = name;
    }

    public int getNum() {//read-only
        return num;
    }




}
