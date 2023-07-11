package com.jeethink.project.dzzlrkxt.domain;/**
 * @ClassName Point
 * @Description
 * @Author lxl
 * @Time 2023/6/29 15:18
 * @Version 1.0
 */

import java.io.Serializable;

/**
 * @ClassName Point
 * @Description TODO
 * @Author lxl
 * @Date 2023/6/29 15:18
 * @Version 1.0
 */
public class MyPoint implements Serializable {
   private String x;
   private String y;

    public MyPoint(String x, String y) {
        this.x=x;
        this.y=y;
    }



    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }
}
