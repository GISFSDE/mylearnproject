package com.gisfsde.springbootlearn;

import com.gisfsde.springbootlearn.controller.HelloController;
import com.gisfsde.springbootlearn.controller.UserController;
import com.gisfsde.springbootlearn.entity.UserJT;
import com.gisfsde.springbootlearn.service.UserJTService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*这里相较1.x版本教程中，主要有两个地方不同。测试类采用@RunWith(SpringRunner.class)和@SpringBootTest修饰启动；
另外，由于POST和PUT接口的参数采用@RequestBody注解，所以提交的会是一个json字符串，而不是之前的参数形式，
这里在定义请求的时候使用contentType(MediaType.APPLICATION_JSON)指定提交内容为json格式，使用content传入要提交的json字符串。
如果用@ModelAttribute的话就得用param方法添加参数*/
//@RunWith：
//　　1.表示运行方式，@RunWith(JUnit4TestRunner)、@RunWith(SpringRunner.class)、@RunWith(PowerMockRunner.class) 三种运行方式，分别在不同的场景中使用。
//　　1.当一个类用@RunWith注释或继承一个用@RunWith注释的类时，JUnit将调用它所引用的类来运行该类中的测试而不是开发者去在junit内部去构建它。我们在开发过程中使用这个特性。

//@SpringBootTest：
//　　1.注解制定了一个测试类运行了Spring Boot环境。提供了以下一些特性：
//　　　　1.1.当没有特定的ContextConfiguration#loader()（@ContextConfiguration(loader=...)）被定义那么就是SpringBootContextLoader作为默认的ContextLoader。
//　　　　1.2.自动搜索到SpringBootConfiguration注解的文件。
//　　　　1.3.允许自动注入Environment类读取配置文件。
//　　　　1.4.提供一个webEnvironment环境，可以完整的允许一个web环境使用随机的端口或者自定义的端口。
//　　　　1.5.注册了TestRestTemplate类可以去做接口调用。
//　　2.添加这个就能取到spring中的容器的实例，如果配置了@Autowired那么就自动将对象注入。

//@WebAppConfiguration：
//　　由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
//@WebIntegrationTest("server.port:0")：
//　　使用0表示端口号随机，也可以具体指定如8888这样的固定端口。不可和@WebAppConfiguration同时使用。

//@BeforeClass：方法只能是static void。
//@AfterClass：方法只能是static void。
//@Before：@Test运行之前调用的方法，可以做初始化操作
//@After：执行完测试用例需要执行的清理工作
//@Test：测试用例的单元
//@Mock：
//　　有点类似Autowired注解，而@Mock注解是自动实现模拟对象，而并非Bean实例创建。
//　　正式环境只是一个接口，并没有实现，也并没有纳入spring容器进行管理。使用BDDMockito对行为进行预测。
//@Ignore("not ready yet")：该方法不执行
//执行顺序是：@BeforeClass→@Before→@Test→@After→@AfterClass
//当启动测试类，测试类中有多个@Test，@BeforeClass和@AfterClass只会执行一次，每一个@Test都会执行一次@Before和@After。


@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootlearnApplicationTests {
    private MockMvc mvc;
    private MockMvc mvc1;

    @Autowired
    private UserJTService userSerivce;
    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
        mvc1 = MockMvcBuilders.standaloneSetup(new UserController()).build();

        // 准备，清空user表
        userSerivce.deleteAllUsers();
    }

    @Test
    public void test() throws Exception {
        // 插入5个用户
        userSerivce.create("Tom", 10);
        userSerivce.create("Mike", 11);
        userSerivce.create("Didispace", 30);
        userSerivce.create("Oscar", 21);
        userSerivce.create("Linda", 17);

        // 查询名为Oscar的用户，判断年龄是否匹配
        List<UserJT> userList = userSerivce.getByName("Oscar");
        Assert.assertEquals(21, userList.get(0).getAge().intValue());

        // 查数据库，应该有5个用户
        Assert.assertEquals(5, userSerivce.getAllUsers());

        // 删除两个用户
        userSerivce.deleteByName("Tom");
        userSerivce.deleteByName("Mike");

        // 查数据库，应该有5个用户
        Assert.assertEquals(3, userSerivce.getAllUsers());

    }


    @Test
    @DisplayName("获取hello测试方法")
    public void getHello() throws Exception {
        mvc.perform(get("/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello World")));
    }

    @Test
    public void getHello1() throws Exception {
        mvc.perform(get("/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello Spring Boot!我帅得一匹22name:我帅得一匹,age:22真的帅21")));
    }

    @Test
    void contextLoads() {
    }
    @Test
    public void testUserController() throws Exception {
        // 测试UserController
        RequestBuilder request;

        // 1、get查一下user列表，应该为空
        request = get("/users/");
        mvc1.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));

        // 2、post提交一个user
        request = post("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"测试大师\",\"age\":20}");
        mvc1.perform(request)
                .andExpect(content().string(equalTo("success")));

        // 3、get获取user列表，应该有刚才插入的数据
        request = get("/users/");
        mvc1.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"测试大师\",\"age\":20}]")));

        // 4、put修改id为1的user
        request = put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"测试终极大师\",\"age\":30}");
        mvc1.perform(request)
                .andExpect(content().string(equalTo("success")));

        // 5、get一个id为1的user
        request = get("/users/1");
        mvc1.perform(request)
                .andExpect(content().string(equalTo("{\"id\":1,\"name\":\"测试终极大师\",\"age\":30}")));

        // 6、del删除id为1的user
        request = delete("/users/1");
        mvc1.perform(request)
                .andExpect(content().string(equalTo("success")));

        // 7、get查一下user列表，应该为空
        request = get("/users/");
        mvc1.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));

    }
}
