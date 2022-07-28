package com.sheep.emo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : sheep669
 * @description : 启动入口类
 * @created at 2022/7/28 18:28
 */
@SpringBootApplication
@MapperScan("com.sheep.emo.mapper")
public class EmoBackBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmoBackBootApplication.class, args);
    }

}
