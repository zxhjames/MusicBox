package com.neteasecommunity.james.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//配置静态资源服务器路径
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Value("${web.upload-path}")
    private String UPLOAD_FOLDER;
    @Override
    //配置用户头像静态资源
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/img/user/**").addResourceLocations("file:"+"/home/zxh/桌面/shop/avatar/");
    }
}
