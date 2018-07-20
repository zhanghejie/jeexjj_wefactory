package com.xjj;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.xjj.wefactory.**.dao")
@ComponentScan("com.xjj.wefactory")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}