package com.markben.rest.server.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 乌草坡
 * @since 0.0.1
 */
@SpringBootApplication
@ComponentScan(value = "com.markben")
@MapperScan("com.markben.**.dao")
public class SpringBootMain {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMain.class, args);
    }

}
