package com.neteasecommunity.james.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Value("${prop.upload-folder}")
    private String UPLOAD_FOLDER;
    @Override
    //配置用户头像静态资源
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/img/user/**").addResourceLocations("file:"+UPLOAD_FOLDER);
    }
}
