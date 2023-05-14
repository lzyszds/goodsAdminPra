package com.example.demo005.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @className: MyMVCconfig.java
 * @author: moyu
 * @date: 2023年04月05日  0:00
 **/
@Configuration
public class MyMVCconfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
    }

    @Autowired
    private MyInterceptor myInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor).addPathPatterns("/**")//拦截所有路由
                //放行路由
                .excludePathPatterns("/login", "/login/**", "/register", "/common/kaptcha")
                .excludePathPatterns("**.png", "**.jpg", "**.json");

    }
}
