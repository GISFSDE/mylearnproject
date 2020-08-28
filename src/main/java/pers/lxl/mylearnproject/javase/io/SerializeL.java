package pers.lxl.mylearnproject.javase.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;

/*系列化就是把一个Java对象变成二进制内容，本质上就是一个byte[]数组
* why？序列化后可以把byte[]保存到文件中，或者把byte[]通过网络传输到远程，
* 这样，就相当于把Java对象存储到文件或者通过网络传输出去了，反序列化相反
* 反序列化时不调用构造方法，可设置serialVersionUID作为版本号（非必需）；
可序列化的Java对象必须实现java.io.Serializable接口，类似Serializable这样的空接口被称为“标记接口”（Marker Interface）
Java的序列化机制仅适用于Java，如果需要与其它语言交换数据，必须使用通用的序列化方法，例如JSON。*/
public class SerializeL {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        //ObjectOutputStream可以写入基本类型,String（以UTF-8编码），实现了Serializable接口的Object
        try (ObjectOutputStream output = new ObjectOutputStream(buffer)) {
            // 写入int:
            output.writeInt(12345);
            // 写入String:
            output.writeUTF("Hello");
            // 写入Object:
            output.writeObject(Double.valueOf(123.456));
        }
        System.out.println(Arrays.toString(buffer.toByteArray()));
    }
}
