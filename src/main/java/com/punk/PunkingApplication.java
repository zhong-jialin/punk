package com.punk;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.punk.dao")
public class PunkingApplication {

    public static void main(String[] args) {
        SpringApplication.run(PunkingApplication.class, args);
    }

}
