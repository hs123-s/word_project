package com.ahuiali.word;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.ahuiali.word.mapper")
@EnableTransactionManagement
@EnableCaching
public class WordApplication {

    public static void main(String[] args) {
        SpringApplication.run(WordApplication.class, args);
    }

}
