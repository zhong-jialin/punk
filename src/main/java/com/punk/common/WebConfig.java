package com.punk.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private JwtInterceptor jwtInterceptor;
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // 指定controller统一的接口前缀
        configurer.addPathPrefix("/punk", clazz -> clazz.isAnnotationPresent(RestController.class));
    }

    // 加自定义拦截器JwtInterceptor，设置拦截规则
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/punk/**")
//                聊天测试
                .excludePathPatterns("/punk/commodity/upload")
                .excludePathPatterns("/punk/commodity/upload")
                .excludePathPatterns("/punk/commodity/export")
                .excludePathPatterns("/punk/commodity/search")
                .excludePathPatterns("/punk/commodity/searchById")
                .excludePathPatterns("/punk/user/login")
                .excludePathPatterns("/punk/files/**")
//                .excludePathPatterns("/punk/shop/**")
                .excludePathPatterns("/punk/user/register");

    }
}
