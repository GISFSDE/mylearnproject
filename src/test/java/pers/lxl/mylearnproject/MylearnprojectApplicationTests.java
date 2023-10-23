package pers.lxl.mylearnproject;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class MylearnprojectApplicationTests {
   String s="{\"success\":true,\"content\":{\"data\":{\"accountId\":79189624,\"mobile\":\"13333333333\",\"employeeCode\":\"GE_f968a98e0f804223bda4713c5dcd9a2b\",\"status\":0},\"success\":true,\"requestId\":\"0aa01be316703162420146844d0011\",\"responseMessage\":\"OK\",\"responseCode\":\"0\",\"bizErrorCode\":\"0\"},\"bizErrorCode\":\"0\"}";
    Map<String, Object> map = new HashMap<>();
    @Test
    void test() {
//        contextLoads(s);
//      DecimalFormat decimalFormat = new DecimalFormat("0.000000000");
//        BigDecimal bigDecimal = new BigDecimal("8.4737948122986E-8");
//        System.out.println(decimalFormat.format(bigDecimal));
//        Map<String,String> map = new HashMap<>();
//        map.put("ST_3X_CX_YL", "1");
//        map.put("ST_3X_HX_YL", "2");
//        map.put("ST_3X_CX_YL", "3");
//        map.forEach((k,v)->{
//            System.out.println("键："+k+"，值："+v);
//            System.out.println("类型："+k.split("_")[2]);
//        });
        String Web_Map_as_JSON="{\"exportOptions\":{\"outputSize\":[800,800],\"dpi\":96},\"operationalLayers\":[{\"visibleLayers\":[0],\"title\":\"Homeland+security+operations\",\"opacity\":0.8,\"url\":\"http://1.1.1.1:8080/68DE38F71E38CD8C508FAB3035752EA8ED8F9221EDE18FDBE593B01FCDD12BD296F09FE27CD7D2733AD075EAF994851B/PBS/rest/services/hzsyvector/Mapserver\"},{\"featureCollection\":{\"layers\":[{\"layerDefinition\":{\"name\":\"polylineLayer\",\"drawingInfo\":{\"renderer\":{\"symbol\":{\"color\":[255,0,0,255],\"width\":4,\"style\":\"esriSLSSolid\",\"type\":\"esriSLS\"},\"type\":\"simple\"}},\"geometryType\":\"esriGeometryPolyline\"},\"featureSet\":{\"features\":[{\"geometry\":{\"paths\":[],\"spatialReference\":{\"wkid\":4490}}}]}}]},\"id\":\"map_graphics\"}],\"mapOptions\":{\"extent\":{\"ymin\":\"29.42064388\",\"xmin\":\"118.82769453\",\"ymax\":\"29.42098832\",\"xmax\":\"118.82797695\",\"spatialReference\":{\"wkid\":4490}},\"scale\":\"2500\",\"spatialReference\":{\"wkid\":4490},\"showAttribution\":true}}";
        JSONObject jsonObject = JSONObject.parseObject(Web_Map_as_JSON);
        System.out.println(jsonObject);
    }


    BigDecimal getMapDecimalSum(){
    Map<String, BigDecimal> map = new HashMap<>();
    map.put("a", new BigDecimal("1.5"));
    map.put("b", new BigDecimal("2"));
    map.put("c", new BigDecimal("2.5"));
      return   map.values().stream().reduce(BigDecimal::add).get();
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
