package pers.lxl.mylearnproject.programbase.tool.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Map;
/**百度地图api实例*/
@RestController
public class MapController {
    private String ak = "E6PF0WPgsEDtgDR3ALF4D1RxSQUsNUV3";
    @Autowired
    RestTemplate restTemplate;
    /**Ip定位（后台）
     * 步骤：1.springmvc工程
     * 2.有请求Request
     * 3.用Request得到请求IP地址
     * 4.调用百度地图服务原"普通IP定位"服务进行查询
     * 5.返回查询信息*/
    @RequestMapping("/getaddr")
    public Object getAddrUserIP(HttpServletRequest request) throws URISyntaxException {
        String ip = request.getRemoteHost();
        System.out.println(ip);
        ip = "112.10.109.83";
        //HTTP协议 "
        String url = "http://api.map.baidu.com/location/ip?ak=" + ak + "&ip=" + ip + "&coor=bd09ll";
//        向百度地图发送请求
        ResponseEntity<Map> forEntity = restTemplate.getForEntity(new URI(url), Map.class);
        Map body = forEntity.getBody();
        return body;
    }

    /**国内查询天气
     * 步骤1.IP定位-直接写行政代码
     * 2.调用国内天气查询服务
     * 3.得到数据，并返回*/
    @RequestMapping("/getWeather")
    public Object getWeatherByIP(HttpServletRequest request) throws URISyntaxException {
//        String ip = request.getRemoteHost();
//        System.out.println(ip);
//        ip = "112.10.109.83";
        //HTTP协议 "
        int code=330100;
        String url = "http://api.map.baidu.com/weather/v1/?district_id="+code+"&data_type=all&ak=" + ak ;
//        向百度地图发送请求
        ResponseEntity<String> forEntity = restTemplate.getForEntity(new URI(url), String.class);

        return forEntity.getBody();
    }
    /**电子围栏打卡
     * 步骤1.围栏范围
     * 2.IP得到经纬度
     * 3.把经纬度上传进行打卡
     * 4.判断是否打卡成功*/
    /**创建实体（终端）*/
    @GetMapping("/creatEntity")
    public Object creatEntity(String name) throws URISyntaxException {
//        post请求
        String url = "http://yingyan.baidu.com/api/v3/entity/add" ;
        LinkedMultiValueMap<String,Object> map=new LinkedMultiValueMap<>();
        map.add("ak",ak);
        map.add("service_id",225727);
        map.add("entity_name",name);
//        向百度地图发送请求
        ResponseEntity<String> post = restTemplate.postForEntity(new URI(url),map,String.class);
        return post.getBody();
    }
    @GetMapping("/addPoint")
    public Object addPoint(Double longitude,Double latitude,String name) throws Exception {
//        post请求
        String url = "http://yingyan.baidu.com/api/v3/track/addpoint" ;
        LinkedMultiValueMap<String,Object> map=new LinkedMultiValueMap<>();
        map.add("ak",ak);
        map.add("service_id",225727);
        map.add("entity_name",name);
        map.add("longitude",longitude);
        map.add("latitude",latitude);
        map.add("loc_time", System.currentTimeMillis()/1000);
        map.add("coord_type_input","bd09ll");
//        向百度地图发送请求
        ResponseEntity<String> post = restTemplate.postForEntity(new URI(url),map,String.class);
        return post.getBody();
    }

    @GetMapping("/queryStatus")
    public Object queryStatus(String name) throws Exception {
        String url = "http://yingyan.baidu.com/api/v3/fence/querystatus?ak="+ak;
     url+="&service_id=225727";
     url+="&monitored_person="+name;
        ResponseEntity<String> get = restTemplate.getForEntity(new URI(url),String.class);
        return get.getBody();
    }

    @GetMapping("/add_fence_entity")
    public Object fenceEntity(int fenceId,String name) throws URISyntaxException {
//        post请求
        String url = "http://yingyan.baidu.com/api/v3/fence/addmonitoredperson" ;
        LinkedMultiValueMap<String,Object> map=new LinkedMultiValueMap<>();
        map.add("ak",ak);
        map.add("service_id",225727);
        map.add("fence_id",fenceId);
        map.add("monitored_person",name);
//        向百度地图发送请求
        ResponseEntity<String> post = restTemplate.postForEntity(new URI(url),map,String.class);
        return post.getBody();
    }

    /**创建围栏（公司打卡范围）*/
    @RequestMapping("/creatFence")
    public Object creatFence() throws URISyntaxException {
//        post请求
        String url = "http://yingyan.baidu.com/api/v3/fence/createcirclefence" ;
        LinkedMultiValueMap<String,Object> map=new LinkedMultiValueMap<>();
        map.add("ak",ak);
        map.add("service_id",225727);
        map.add("longitude",120.10784900531651);
        map.add("latitude",30.33892818744006);
        map.add("radius",5000);
        map.add("coord_type","bd09ll");
//        向百度地图发送请求
        ResponseEntity<String> post = restTemplate.postForEntity(new URI(url),map,String.class);

        return post.getBody();
    }
}