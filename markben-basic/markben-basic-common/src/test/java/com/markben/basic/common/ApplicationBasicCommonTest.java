package com.markben.basic.common;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @autor 乌草坡 2020-02-28
 * @since 1.0
 */
@SpringBootApplication
@MapperScan(value = {"com.markben.basic.common.mapper"})
@ComponentScan(value = {"com.markben"})
public class ApplicationBasicCommonTest {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationBasicCommonTest.class, args);
    }

}
