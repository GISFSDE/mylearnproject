package pers.lxl.mylearnproject.frame.spring.annotation.pojo;

import org.springframework.stereotype.Component;

@Component("landlord")
public class Landlord {
//以某个类额某个方法作为连接点，另一种说法就是：选择哪一个类的哪一方法用以增强功能。
    public void service() {
        // 仅仅只是实现了核心的业务功能
        System.out.println("签合同");
        System.out.println("收房租");
    }
}

