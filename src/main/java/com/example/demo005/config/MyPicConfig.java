package com.example.demo005.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MyPicConfig implements WebMvcConfigurer {
    //配置静态资源映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置静态资源映射 为了防止路径有问题，使用System.getProperty("user.dir") 这样就再也不怕路径出问题了。换到其它电脑上也没有问题
        registry.addResourceHandler("/**").
                addResourceLocations("file:" + System.getProperty("user.dir") + "/src/main/resources/static/");
    }
}
