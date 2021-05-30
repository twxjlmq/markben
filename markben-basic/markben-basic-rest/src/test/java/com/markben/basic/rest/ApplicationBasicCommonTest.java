package com.markben.basic.rest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @autor 乌草坡
 * @since 0.0.1
 */
@SpringBootApplication
@MapperScan(value = {"com.markben.basic.dao"})
@ComponentScan(value = {"com.markben"})
public class ApplicationBasicCommonTest {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationBasicCommonTest.class, args);
    }

}
