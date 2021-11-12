package com.project.yjshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class YjShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(YjShopApplication.class, args);
    }

}
