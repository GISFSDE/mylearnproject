package pers.lxl.mylearnproject.javase.io.io;

import java.io.*;
import java.util.Arrays;

/**---对象操作----
 * 序列化就是把一个Java对象变成二进制内容，本质上就是一个byte[]数组
* why？序列化后可以把byte[]保存到文件中，或者把byte[]通过网络传输到远程，
* 这样，就相当于把Java对象存储到文件或者通过网络传输出去了，反序列化相反
* 反序列化时不调用构造方法，可设置serialVersionUID作为版本号（非必需）；
可序列化的Java对象必须实现java.io.Serializable接口，类似Serializable这样的空接口被称为“标记接口”（Marker Interface）

 transient 关键字可以使一些属性不会被序列化。ArrayList 中存储数据的数组 elementData 是用 transient 修饰的，因为这个数组是动态扩展的，并不是所有的空间都被使用，因此就不需要所有的内容都被序列化。通过重写序列化和反序列化方法，使得可以只序列化数组中有内容的那部分数据。
 private transient Object[] elementData;

Java的序列化机制仅适用于Java，如果需要与其它语言交换数据，必须使用通用的序列化方法，例如JSON。
 * @author lxl**/
public class SerializeL {
    public static void main(String[] args) throws IOException {
        //创建一个Hero garen
        //要把Hero对象直接保存在文件上，务必让Hero类实现Serializable接口
        Hero h = new Hero();
        h.name = "garen";
        h.hp = 616;

        //准备一个文件用于保存该对象
        File f =new File("fileTest.txt");

        try(
                //创建对象输出流
                FileOutputStream fos = new FileOutputStream(f);
//                序列化
                ObjectOutputStream oos =new ObjectOutputStream(fos);
                //创建对象输入流
                FileInputStream fis = new FileInputStream(f);
//                反序列化
                ObjectInputStream ois =new ObjectInputStream(fis);
//                不会对静态变量进行序列化，因为序列化只是保存对象的状态，静态变量属于类的状态。
        ) {
            oos.writeObject(h);
            Hero h2 = (Hero) ois.readObject();
            System.out.println(h2.name);
            System.out.println(h2.hp);

        } catch (IOException e) {

            e.printStackTrace();
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }








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
