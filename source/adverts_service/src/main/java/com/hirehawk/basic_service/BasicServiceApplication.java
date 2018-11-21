package com.hirehawk.basic_service;import org.springframework.boot.SpringApplication;import org.springframework.boot.autoconfigure.SpringBootApplication;import org.springframework.context.annotation.Bean;import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;import org.springframework.web.servlet.config.annotation.CorsRegistry;import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;@SpringBootApplication@EnableMongoRepositories(basePackages = {"com.hirehawk.basic_service.testmongo"})public class BasicServiceApplication {    public static void main(String[] args) {        SpringApplication.run(BasicServiceApplication.class, args);           }}