package pers.lxl.mylearnproject;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class MylearnprojectApplicationTests {
   String s="{\"success\":true,\"content\":{\"data\":{\"accountId\":79189624,\"mobile\":\"13333333333\",\"employeeCode\":\"GE_f968a98e0f804223bda4713c5dcd9a2b\",\"status\":0},\"success\":true,\"requestId\":\"0aa01be316703162420146844d0011\",\"responseMessage\":\"OK\",\"responseCode\":\"0\",\"bizErrorCode\":\"0\"},\"bizErrorCode\":\"0\"}";
    Map<String, Object> map = new HashMap<>();
    @Test
    void test() {
        contextLoads(s);
    }


    void contextLoads(String s) {

        Map<String, Object> Mapjson=(Map)JSONObject.parse(s);



        //循环转换
        for (Map.Entry<String, Object> entry : Mapjson.entrySet()) {
            if(entry.getValue() instanceof JSONObject) {
                contextLoads(entry.getValue().toString());
            }else{
                map.put(entry.getKey(), entry.getValue());}
        }

        System.out.println(map.toString());
    }
}
