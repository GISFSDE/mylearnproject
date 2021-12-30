package pers.lxl.mylearnproject.javase.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

//https://docs.spring.io/spring-boot/docs/2.2.x/reference/html/spring-boot-features.html#boot-features-testing
//The starter also brings the vintage engine so that you can run both JUnit 4 and JUnit 5 tests. If you have migrated your tests to JUnit 5, you should exclude JUnit 4 support, as shown in the following example:
//
//<dependency>
//    <groupId>org.springframework.boot</groupId>
//    <artifactId>spring-boot-starter-test</artifactId>
//    <scope>test</scope>
//    <exclusions>
//        <exclusion>
//            <groupId>org.junit.vintage</groupId>
//            <artifactId>junit-vintage-engine</artifactId>
//        </exclusion>
//    </exclusions>
//</dependency>

//junit4
//@RunWith(SpringRunner.class)
//junit5
@ExtendWith(SpringExtension.class)
@SpringBootTest
class HelloClassTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getString() throws InterruptedException {
        String s = "ss";
    }

//    /**超时测试**/
//    @Test(timeout = 1000)
//    public void testTimeout() throws InterruptedException {
//        TimeUnit.SECONDS.sleep(2);
//        System.out.println("Complete");
//    }
//    /**异常测试**/
//    @Test(expected = NullPointerException.class)
//    public void testNullException() {
//        throw new NullPointerException();
//    }

/** junit4 套件测试**/
//    public class TaskOneTest {
//        @Test
//        public void test() {
//            System.out.println("Task one do.");
//        }
//    }
//
//    public class TaskTwoTest {
//        @Test
//        public void test() {
//            System.out.println("Task two do.");
//        }
//    }
//
//    public class TaskThreeTest {
//        @Test
//        public void test() {
//            System.out.println("Task Three.");
//        }
//    }
//
//    @RunWith(Suite.class) // 1. 更改测试运行方式为 Suite
//// 2. 将测试类传入进来
//    @Suite.SuiteClasses({TaskOneTest.class, TaskTwoTest.class, TaskThreeTest.class})
//    public class SuitTest {
//        /**
//         * 测试套件的入口类只是组织测试类一起进行测试，无任何测试方法，
//         */
//    }

//    /** ExtendWith 参数化测试**/
    //1.更改默认的测试运行器为RunWith(Parameterized.class)
//    @RunWith(Parameterized.class)
//    public class ParameterTest {
//        // 2.声明变量存放预期值和测试数据
//        private String firstName;
//        private String lastName;
//
//        //3.声明一个返回值 为Collection的公共静态方法，并使用@Parameters进行修饰
//        @Parameterized.Parameters //
//        public  List<Object[]> param() {
//            // 这里我给出两个测试用例
//            return Arrays.asList(new Object[][]{{"Mike", "Black"}, {"Cilcln", "Smith"}});
//        }
//
//        //4.为测试类声明一个带有参数的公共构造函数，并在其中为之声明变量赋值
//        public ParameterTest(String firstName, String lastName) {
//            this.firstName = firstName;
//            this.lastName = lastName;
//        }
//        // 5. 进行测试，发现它会将所有的测试用例测试一遍
//        @Test
//        public void test() {
//            String name = firstName + " " + lastName;
//            assertThat("Mike Black", is(name));
//        }
//    }
}
