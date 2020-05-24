package com.jiadao;

// import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 启动程序
 * 
 * @author 
 */
@SpringBootApplication
@EnableCaching  //开启缓存
public class WebApplication extends SpringBootServletInitializer 
{
    public static void main(String[] args)
    { 
        System.out.println("api-gen系统启动中");
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(WebApplication.class, args);
        System.out.println("api-gen系统启动成功");
    }

    // 不重写打包war部署到tomcat接口会报404
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApplication.class);
    }

}