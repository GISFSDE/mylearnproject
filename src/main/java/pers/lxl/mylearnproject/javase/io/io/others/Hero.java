package pers.lxl.mylearnproject.javase.io.io.others;

import java.io.Serializable;
/**序列化的类需要实现 Serializable 接口，它只是一个标准，没有任何方法需要实现，但是如果不去实现它的话而进行序列化，会抛出异常。
 */
public class Hero implements Serializable {
    /**表示这个类当前的版本，如果有了变化，比如新设计了属性，就应该修改这个版本号*/
    private static final long serialVersionUID = 1L;
    public String name;
    public float hp;
}
