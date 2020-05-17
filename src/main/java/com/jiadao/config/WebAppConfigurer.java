package com.jiadao.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfigurer implements WebMvcConfigurer{  

    /**
     * 配置一个支持返回bufferedimage的转换器
     * @return
     */
    @Bean
    public BufferedImageHttpMessageConverter bufferedImageMessageConverters() {
 
       return new BufferedImageHttpMessageConverter();
    }

    /**
     * Controller 方法参数注入CurrentUser
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new CurrentUserArgumentResolver());
    }

    /**
     * 配置拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       //注册当前的拦截器
       registry.addInterceptor(new JWTTokenInterceptor());
    }
}