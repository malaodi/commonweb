package com.module;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

import java.io.IOException;
import java.util.Properties;

/**
 * springboot程序启动类
 */
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@MapperScan("com.module.mapper")
public class CommonwebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonwebApplication.class, args);
        PageHelperConfig.showStartInfo(); //控制台显示系统访问路径
    }


}
