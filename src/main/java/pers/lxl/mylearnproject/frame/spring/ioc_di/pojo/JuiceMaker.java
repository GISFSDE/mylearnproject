package pers.lxl.mylearnproject.frame.spring.ioc_di.pojo;

public class JuiceMaker {

    // 唯一关联了一个 Source 对象
    private Source source = null;

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String makeJuice(){
        String juice = "xxx用户点了一杯" + source.getFruit() + source.getSugar() + source.getSize();
        return juice;
    }
}
