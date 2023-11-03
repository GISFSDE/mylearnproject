package pers.lxl.mylearnproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 由注解源代码可知
 *
 * @author lxl
 * @springbootConfigration组合了:
 * @Documented
 * @Inherited
 * @SpringBootConfiguration表明我们这个类将会处理spring的常规配置
 * @EnableAutoConfiguration
 * @ComponentScan 告诉spring在哪去找spring的组件（服务，控制器等），默认扫描当前包下所有子包
 */
/** 点击左侧按钮可在代码中查看图片，注释中可以添加html代码
 * The following is the design idea of this class:
 * <img width="640" height="320" src="https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/legend.png" alt="">
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, MongoAutoConfiguration.class})
public class MylearnprojectApplication {
    public static void main(String[] args) {
        SpringApplication.run(MylearnprojectApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }
}
