package com.sheep.emo.config;

/**
 * @author : sheep669
 * @description : swagger的配置类
 * @created at 2022/7/9 23:08
 */

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * 创建接口文档信息
     */
    public ApiInfo createApiInfo() {
        return new ApiInfoBuilder()
                .title("emo在线团购系统后台接口文档")
                .description("学习永无止境")
                .version("V1.0.0")
                .termsOfServiceUrl("https://blog.nowcoder.net/visya")
                .contact(new Contact("sheep669",
                        "https://blog.nowcoder.net/visya",
                        "sheep669@126.com"))
                .build();
    }

    @Bean
    public Docket createApi() {
        return new Docket(DocumentationType.SWAGGER_2).
                apiInfo(createApiInfo())
                .groupName("sheep")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .build();
    }

}

