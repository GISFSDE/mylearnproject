package pers.lxl.mylearnproject.javase.io.io.others;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lxl
 * @Date: 2022/10/26/18:31
 * @Description: 创建文件夹
 */
public class CreateDir {
    public static void main(String[] args) {
//      一、父文件夹不存在 都可以创建
        File file = new File("D:\\CaptureTest\\test");
//      二、父文件夹存在，最后一级存在不可创建
        file.delete();
        boolean mkdir = file.mkdir();
//      删除最后一级空文件夹
        System.out.println(mkdir);
        file.delete();

        boolean mkdirs = file.mkdirs();
        System.out.println(mkdirs);
    }
}
