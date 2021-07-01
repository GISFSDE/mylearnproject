package pers.lxl.mylearnproject.programbase.designpatterns;
/**Design Patterns软件设计中，被反复使用的一种代码设计经验。
使用设计模式的目的是为了可重用代码，提高代码的可扩展性和可维护性。
 设计模式把一些常用的设计思想提炼出一个个模式，然后给每个模式命名，这样在使用的时候更方便交流。
 切记滥用，不同设计模式适用不同场景
学习设计模式，记住两种角色，作者（服务器端程序员）用户（客户端程序员）c3p0
设计模式中的接口只下层更上层暴露出来的


开闭原则
    由Bertrand Meyer提出的开闭原则（Open Closed Principle）是指，软件应该对扩展开放，
    而对修改关闭。这里的意思是在增加新功能的时候，能不改代码就尽量不要改，
    如果只增加代码就完成了新功能，那是最好的。一个类从头到尾都是自己写的可以更改，别人的要符合开闭原则

里氏替换原则
    里氏替换原则是Barbara Liskov提出的，这是一种面向对象的设计原则，
    即如果我们调用一个父类的方法可以成功，那么替换成子类调用也应该完全可以运行。
    重写时子类限制等级不能更高，错误不能抛出更多

 单一职责原则
    每个方法，类，框架只负责一件事

 依赖倒置原则
    上层（调用别的方法的）不能依赖于下层（方法被调用的），他们都应该依赖于抽象


 迪米特原则（最少知道原则）
一个对象应对其它对象尽可能少的了解，和朋友（类中字段，方法参数，方法返回值，方法实例出来的对象）通信

 接口隔离原则
    使用多个专门的接口比一个总接口好

 组合优于继承原则
 继承（实线空箭头）
 依赖
 关联（实线箭头）：组合（实心菱形加箭头）（鸟和翅膀）；聚合（空心菱形加箭头）（雁和雁群）

 分

 GoF把23个常用模式分为
创建型模式  -提供创建对象的机制， 增加已有代码的灵活性和可复用性。
结构型模式  -介绍如何将对象和类组装成较大的结构， 并同时保持结构的灵活和高效。
行为型模式  -负责对象间的高效沟通和职责委派。

学习设计模式，关键是学习设计思想，不能简单地生搬硬套，
也不能为了使用设计模式而过度设计，要合理平衡设计的复杂度和灵活性，
并意识到设计模式也并不是万能的。
算法更像是菜谱： 提供达成目标的明确步骤。 而模式更像是蓝图：
 你可以看到最终的结果和模式的功能， 但需要自己确定实现步骤。

 意图  部分简单描述问题和解决方案。
动机  部分将进一步解释问题并说明模式会如何提供解决方案。
结构  部分展示模式的每个部分和它们之间的关系。
在不同语言中的实现   提供流行编程语言的代码， 让读者更好地理解模式背后的思想。

 */
//http://c.biancheng.net/view/8462.html  一句话全模式总结
public class DesignPrinciple {
}
