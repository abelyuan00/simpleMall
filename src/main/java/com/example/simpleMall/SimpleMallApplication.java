package com.example.simpleMall;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
@MapperScan("com/example/simpleMall/Dao")
public class SimpleMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleMallApplication.class, args);
    }

}
