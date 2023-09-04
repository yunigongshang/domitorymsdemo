package com.xunfang.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xunfang.demo.mapper")
public class DomitorymsdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DomitorymsdemoApplication.class, args);

    }

}
