package com.markben.basic.rest.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 乌草坡
 * @since 1.0
 */
@SpringBootApplication
@ComponentScan(value = "com.markben")
@MapperScan("com.markben.**.mapper")
public class SpringBootMain {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMain.class, args);
    }

}