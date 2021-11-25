package pers.lxl.mylearnproject.programbase.jvm;


import java.util.ArrayList;
import java.util.Random;

public class OOMInstance {
    public static void main(String[] args){
    ArrayList<Picture1> list=new ArrayList<>();
            while(true)

    {

        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list.add(new Picture1(new Random().nextInt(1024*1024)));
    }
    }
}

class Picture1{
    private  byte[] pixels;
    public Picture1(int length){
        this.pixels = new byte[length];
    }
}