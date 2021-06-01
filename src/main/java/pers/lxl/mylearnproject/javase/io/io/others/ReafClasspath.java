package pers.lxl.mylearnproject.javase.io.io.others;

import java.io.IOException;
import java.io.InputStream;

/*在classpath中的资源文件，路径总是以／开头，我们先获取当前的Class对象，
然后调用getResourceAsStream()就可以直接从classpath读取任意的资源文件*/
public class ReafClasspath {
    public static void main(String[] args) throws IOException {
        try (InputStream inputStream = ReafClasspath.class.getResourceAsStream("/setting.properties")) {

            if (inputStream != null) {//资源不存在返回null
                System.out.println("you");
            }
        }
    }

}
