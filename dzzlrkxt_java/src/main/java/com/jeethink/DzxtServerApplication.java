package com.jeethink;

import com.jeethink.common.utils.IdWorker;
import com.jeethink.project.dzzlrkxt.common.util.BeanUtil;
import com.jeethink.project.dzzlrkxt.view.IndexlApplication;
import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.InitBinderDataBinderFactory;
import org.springframework.web.method.support.InvocableHandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ServletRequestDataBinderFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 启动程序
 *
 * @author 世舆  官方网址
 */

@EnableTransactionManagement
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DzxtServerApplication implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(DzxtServerApplication.class);

    public static void main(String[] args) {
        System.out.println("(♥◠‿◠)ﾉﾞ  框架启动成功   ლ(´ڡ`ლ)ﾞ  \n");
//        System.setProperty("spring.main.allow-circular-references", "true");
        // System.setProperty("spring.devtools.restart.enabled", "false");、

        // -Djava.awt.headless=false 地图组件使用
        try {
            SpringApplication.run(DzxtServerApplication.class, args);
        } catch (Exception e) {
            log.warn(e.getMessage());
        }


    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }

    @Bean
    public WebMvcRegistrations mvcRegistrations() {
        return new WebMvcRegistrations() {
            @Override
            public RequestMappingHandlerAdapter getRequestMappingHandlerAdapter() {
                return new ExtendedRequestMappingHandlerAdapter();
            }
        };
    }

    @Bean
    public BeanUtil beanUtil() {
        return new BeanUtil();
    }

    @Override
    public void run(String... args) throws Exception {
        Application.launch(IndexlApplication.class, args);
    }


    private static class ExtendedRequestMappingHandlerAdapter extends RequestMappingHandlerAdapter {

        @Override
        protected InitBinderDataBinderFactory createDataBinderFactory(List<InvocableHandlerMethod> methods) {

            return new ServletRequestDataBinderFactory(methods, getWebBindingInitializer()) {

                @Override
                protected ServletRequestDataBinder createBinderInstance(
                        Object target, String name, NativeWebRequest request) throws Exception {

                    ServletRequestDataBinder binder = super.createBinderInstance(target, name, request);
                    String[] fields = binder.getDisallowedFields();
                    List<String> fieldList = new ArrayList<>(fields != null ? Arrays.asList(fields) : Collections.emptyList());
                    fieldList.addAll(Arrays.asList("class.*", "Class.*", "*.class.*", "*.Class.*"));
                    binder.setDisallowedFields(fieldList.toArray(new String[]{}));
                    return binder;
                }
            };
        }
    }
}
