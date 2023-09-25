package com.sap.hanesbrand;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sap.cloud.sdk", "com.sap.hanesbrand"})
@ServletComponentScan(basePackages = {"com.sap.cloud.sdk","com.sap.hanesbrand"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }


}
