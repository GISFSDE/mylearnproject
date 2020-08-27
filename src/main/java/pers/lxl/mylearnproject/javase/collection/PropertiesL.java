package pers.lxl.mylearnproject.javase.collection;

import java.io.FileOutputStream;
import java.util.Properties;

public class PropertiesL {
    public static void main(String[] args) {
        String f = "setting.properties";
        Properties props = new Properties();
        props.setProperty("language", "Java");
//        props.store(new FileOutputStream("C:\\conf\\setting.properties"), "这是写入的properties注释");
//        props.load(new java.io.FileInputStream(f));
//        props.load(getClass().getResourceAsStream("setting.properties"));
        String filepath = props.getProperty("last_open_file");
        String interval = props.getProperty("auto_save_interval", "120");
    }
}
