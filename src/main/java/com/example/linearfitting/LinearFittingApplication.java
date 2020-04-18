package com.example.linearfitting;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author
 */
@SpringBootApplication
@EnableSwagger2Doc
@MapperScan("com.example.linearfitting.mapper")
public class LinearFittingApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(LinearFittingApplication.class, args);
    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application;
    }

}
