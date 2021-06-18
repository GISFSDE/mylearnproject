//package pers.lxl.mylearnproject.programbase.xmljson;
//
//
//
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import net.minidev.json.JSONArray;
//import net.sf.json.JSON;
//import net.sf.json.xml.XMLSerializer;
//import pers.lxl.mylearnproject.javase.oop.Main;
//import net.sf.json.JSONObject;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**JSON(JavaScript Object Notation)
//* JSON只允许使用UTF-8编码，不存在编码问题；
//JSON只允许使用双引号作为key，特殊字符用\转义，格式简单；
//浏览器内置JSON支持，如果把数据用JSON发送给浏览器，可以用JavaScript直接处理。
//* 支持格式：
//键值对：{"key": value}
//数组：[1, 2, 3]
//字符串："abc"
//数值（整数和浮点数）：12.34
//布尔值：true或false
//空值：null
// 使用json前的准备工作，需要准备下面的六个jar包：
// commons-lang-1.0.4.jar
// commons-collections-2.1.jar
// commons-beanutils-1.8.0.jar
// json-lib-2.4.jar
// ezmorph-1.0.6.jar
// commons-logging-1.1.jar
// 需要说明几点：
// （1）json-lib最新版本可以从这个地方下载：http://sourceforge.net/projects/json-lib/files/json-lib/
// （2）ezmorph是一个简单的java类库，用于将一种bean转换成另外一种bean。其动态bean的实现依赖于commons-beanutils包。ezmorph可以在这个地方下载源码：http://sourceforge.net/projects/ezmorph/files/ezmorph/
// （3）commons-beanutils是操作Java Bean的类库，依赖于commons-collections。
// （4）commons-collections类库是各种集合类和集合工具类的封装。*/
//public class JsonL {
//    public static void main(String[] args) throws IOException {
//对象转MAP
//Map<String, Object> param = JSONObject.parseObject(JSONObject.toJSONString(对象));

//        InputStream input = Main.class.getResourceAsStream("/book.json");
//        ObjectMapper mapper = new ObjectMapper();
//        // 反序列化时忽略不存在的JavaBean属性:
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        Book book = mapper.readValue(input, Book.class);
//        String json = mapper.writeValueAsString(book);
//        ObjectMapper mapper1 = new ObjectMapper().registerModule(new JavaTimeModule());
//        System.out.println(json);
//    }
//        /**
//         *后台怎么拼装JSON格式的字符串
//         */
//
//        public String javaToJSON(){
//            JSONObject jsonObj = new JSONObject();
//            jsonObj.put("username", "张三");
//            jsonObj.put("password", "123456");
//            //{"password", "123456","username", "张三"}
//            return jsonObj.toString();
//        }
//
///**
// *JSON格式的字符串转换成XML格式的字符串
// */
//
//        public String jsonToXML() {
//            String jsonStr = "{\"password\":\"123456\",\"username\":\"张三\"}";
//            JSONObject json = JSONObject.fromString(jsonStr);
//            XMLSerializer xmlSerializer = new XMLSerializer();
//            xmlSerializer.setRootName("user_info");
//            xmlSerializer.setTypeHintsEnabled(false);
//            String xml = xmlSerializer.write(json);
//            return xml;
//        }
//
//        /**
//         *XML格式的字符串转换成JSON格式的字符串
//         */
//        public String xmlToJSON(){
//            String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><user_info><password>123456</password><username>张三</username></user_info>";
//            JSON json=XMLSerializer.read(xml);
//            return json.toString();
//        }
//
//
//    /**
//     * 将map集合转换为json语句表示
//     *
//     * @param map 集合
//     * @return 得到的Map解析的json语句
//     */
//    public String mapToJson(Map<Object, Object> map) {
//        JSONObject jsonObject = JSONObject.fromObject(map);     //将集合解析为 json对象语句(net.sf.json.JSONObject)
//        return jsonObject.toString();                              //返回json语句
//    }
//
//    /**
//     * 将json格式封装的字符串数据转换成java中的Map数据
//     * @return
//     */
//    private Map<Object, Object> jsonToMap(HttpServletRequest req) {
//
//        String jsonStr = req.getParameter("User"); //以参数的形式接收前端传过来的数据
//        Map<Object, Object> map = new HashMap<Object, Object>();
//        JSONArray jsonArray = JSONArray.fromObject(jsonStr);
//        JSONObject jsonOne;
//        for (int i=0;i<jsonArray.size();i++) {
//            jsonOne = jsonArray.getJSONObject(i);
//            map.put("username", (String) jsonOne.get("name"));
//            map.put("age", jsonOne.getString("value"));
//        }
//        return map;   //这才完成JSON向Map数据结构的转换，便于在后台传递并处理。
//    }
//
//
//    /**
//     * 将json格式封装的列表数据转换成java的List数据 [net.sf.ezmorph.bean.MorphDynaBean@1eb605e7[
//     {name=username, value=wwww}],...]
//     ])
//     * @return
//     */
//    private static Object JSON2List(String json) {
//        return JSONArray.toList(JSONArray.fromObject(json));
//    }
//
//    /**
//     * 只包含基本数据类型的简单实体类(getter、setetr方法略)
//     */
//    public class SimpleUser {
//
//        private int id;
//
//        private String name;
//
//        private String sex;
//
//        private int age;
//
//        private String province;
//    }
//    /**
//     * 包含复杂类型的实体类
//     */
//    public class ComplexUser extends SimpleUser {
//
//        private List<String> address;
//
//        public ComplexUser() {
//        }
//
//        public ComplexUser(int id, String name,
//                           String sex, int age, String province,
//                           List<String> address) {
//            super(id, name, sex, age, province);
//            this.address = address;
//        }
//
//        public List<String> getAddress() {
//            return address;
//        }
//
//        public void setAddress(List<String> address) {
//            this.address = address;
//        }
//
//    }
//    /**
//     * 将json格式封装的简单实体类型数据转换成简单类型的javabean（只包含简单的数据类型）
//     */
//    private Object JSONtoSimpleBean() {
//        String jsonStr = "{\"age\":23,\"id\":123,\"name\":\"tt_2009\"," +
//                "\"province\":\"上海\",\"sex\":\"男\"}";
//        JSONObject jsonBean = JSONObject.fromObject(jsonStr);
//        return JSONObject.toBean(jsonBean, SimpleUser.class);
//    }
//    /**
//     * 将json格式封装的复杂实体数据转换成复杂类型的javabean（对象中包含其它复杂对象，如收货地址用List存储）
//     */
//    private static Object JSON2ComplexBean() {
//        String jsonStr = "{\"address\":[\"北京\",\"上海\",\"广州\"]," +
//                "\"age\":23,\"id\":123,\"name\":\"tt_2009\",\"province\":\"上海\",\"sex\":\"男\"}";
//        JSONObject jsonBean = JSONObject.fromObject(jsonStr);
//        return JSONObject.toBean(jsonBean, ComplexUser.class);
//}
