package pers.lxl.mylearnproject.programbase.xmljson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import pers.lxl.mylearnproject.javase.oop.Main;

import java.io.IOException;
import java.io.InputStream;

/*JSON(JavaScript Object Notation)
* JSON只允许使用UTF-8编码，不存在编码问题；
JSON只允许使用双引号作为key，特殊字符用\转义，格式简单；
浏览器内置JSON支持，如果把数据用JSON发送给浏览器，可以用JavaScript直接处理。
* 支持格式：
键值对：{"key": value}
数组：[1, 2, 3]
字符串："abc"
数值（整数和浮点数）：12.34
布尔值：true或false
空值：null*/
public class JsonL {
    public static void main(String[] args) throws IOException {
        InputStream input = Main.class.getResourceAsStream("/book.json");
        ObjectMapper mapper = new ObjectMapper();
// 反序列化时忽略不存在的JavaBean属性:
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Book book = mapper.readValue(input, Book.class);
        String json = mapper.writeValueAsString(book);
        ObjectMapper mapper1 = new ObjectMapper().registerModule(new JavaTimeModule());
    }

}
