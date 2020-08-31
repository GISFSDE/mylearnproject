package pers.lxl.mylearnproject.javase.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import java.lang.reflect.Executable;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertThrows;

public class JUnitT {
    //JUnit
//非常简单地组织测试代码，并随时运行它们，JUnit就会给出成功的测试和失败的测试，
// 还可以生成测试报告，不仅包含测试的成功率，还可以统计测试的代码覆盖率，即被测试的代码本身有多少经过了测试。
// 对于高质量的代码来说，测试覆盖率应该在80%以上。
//此外，几乎所有的IDE工具都集成了JUnit，这样我们就可以直接在IDE中编写并运行JUnit测试。
    //一是单元测试代码本身必须非常简单，能一下看明白，决不能再为测试代码编写测试；
    //二是每个单元测试应当互相独立，不依赖运行的顺序；
    //三是测试时不但要覆盖常用测试用例，还要特别注意测试边界条件，例如输入为0，null，空字符串""等情况。

    @Test
    public void testFact() {
        assertEquals(0, FactorialT.fact(0));
        assertEquals(0, FactorialT.fact(1));
        assertEquals(2, FactorialT.fact(2));
        assertEquals(6, FactorialT.fact(3));
        assertEquals(3628800, FactorialT.fact(10));
    }
    //----条件测试----
//    @Disabled这种注解就称为条件测试，JUnit根据不同的条件注解，决定是否运行当前的@Test方法
    //比如判断平台@EnabledOnOs(OS.WINDOWS)/@DisabledOnOs(OS.WINDOWS)/@DisabledOnJre(JRE.JAVA_8)...
//@Disabled//标记后测试不会运行，去掉@TestJunit不知道这是个测试方法
    //----异常测试----
//    @Test
//    void testNegative() {
//        assertThrows(IllegalArgumentException.class, new Executable() {
//            @Override
//            public void execute() throws Throwable {
//                FactorialT.fact(-1);
//            }
//        });
//    }
//-----参数化测试-----
    //@ParameterizedTest
//假设我们想对Math.abs()进行测试，先用一组正数进行测试：
    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 5, 100 })
    void testAbs(int x) {
        assertEquals(x, Math.abs(x));
    }
//    再用一组负数进行测试：
    @ParameterizedTest
    @ValueSource(ints = { -1, -5, -100 })
    void testAbsNegative(int x) {
        assertEquals(-x, Math.abs(x));
    }
    //当需要输出预期输出时？测试方法需要接受两个参数，如何传递？
    //@MethodSource注解，它允许我们编写一个同名的静态方法来提供测试参数：
    //@CsvSource它的每一个字符串表示一行，一行包含的若干参数用,分隔,参数过多
    //把测试数据提到一个独立的CSV文件中，然后标注上@CsvFileSource
    //JUnit只在classpath中查找指定的CSV文件




    //JUnit提供了编写测试前准备、测试后清理的固定代码，我们称之为Fixture,会在运行每个@Test方法前后自动运行
    //对于实例变量，在@BeforeEach中初始化，在@AfterEach中清理，它们在各个@Test方法中互不影响，因为是不同的实例；
    //对于静态变量，在@BeforeAll中初始化，在@AfterAll中清理，它们在各个@Test方法中均是唯一实例，会影响各个@Test方法。
    FactorialT factorialT;
    @BeforeEach
    public void setUp (){
        this.factorialT = new FactorialT();
    }

    @AfterEach
    public void tearDown(){
        this.factorialT=null;
    }

}
