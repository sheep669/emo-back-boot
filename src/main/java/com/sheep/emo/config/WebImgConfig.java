package com.sheep.emo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author : sheep669
 * @description : TODO
 * @created at 2022/9/27 8:32
 */
@Configuration
public class WebImgConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**").addResourceLocations("file:C:\\Users\\CodeHaywire\\Desktop\\emo-back-boot\\src\\main\\resources\\upload\\");
    }
}

