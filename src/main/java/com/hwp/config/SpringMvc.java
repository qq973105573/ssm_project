package com.hwp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
/*
配置spring与mvc整合配置类
a.开启mvc注解支持
b.设置扫描controller层
c.设置静态资源放行
 */
@EnableWebMvc
@ComponentScan(basePackages = "com.hwp.controller")
public class SpringMvc implements WebMvcConfigurer {

    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();//静态资源放行
    }

    @Bean
    public InternalResourceViewResolver getViewResolver(){
        return new InternalResourceViewResolver("/WEB-INF/html",".html");
    }
    //文件上传解析器配置
    @Bean("multipartResolver")
    public MultipartResolver getMultipartResolver(){
        return new CommonsMultipartResolver();
    }
}
