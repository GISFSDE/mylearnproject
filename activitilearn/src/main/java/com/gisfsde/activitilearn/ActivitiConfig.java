package com.gisfsde.activitilearn;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActivitiConfig implements ProcessEngineConfigurationConfigurer {

    /**
     * 解決工作流生成图片乱码问题
     *
     * @param springProcessEngineConfiguration processEngineConfiguration
     */
    @Override
    public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        springProcessEngineConfiguration.setActivityFontName("宋体");
        springProcessEngineConfiguration.setAnnotationFontName("宋体");
        springProcessEngineConfiguration.setLabelFontName("宋体");
    }
}