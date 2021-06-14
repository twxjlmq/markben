package com.markben.core.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @autor 乌草坡 2020-02-28
 * @since 1.0
 */
@SpringBootApplication
@MapperScan(value = {"com.markben.core"})
public class ApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationTest.class, args);
    }

}
