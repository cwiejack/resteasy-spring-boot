package com.test.resourcesandprovidersperapp;

import com.sample.app.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ResourcesProvidersPerJaxrsAppBootApplication {

    @Bean
    public ResourcesProvidersPerApplication jaxrsApplication() {
        return new ResourcesProvidersPerApplication();
    }

    public static void main(String[] args) {
        SpringApplication.run(ResourcesProvidersPerJaxrsAppBootApplication.class, args);
    }
}
