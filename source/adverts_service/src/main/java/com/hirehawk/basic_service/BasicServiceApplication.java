package com.hirehawk.basic_service;

import com.hirehawk.basic_service.testmongo.Advert;
import com.hirehawk.basic_service.testmongo.AdvertRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@SpringBootApplication
@EnableMongoRepositories(basePackages = {"com.hirehawk.basic_service.testmongo"})
public class BasicServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicServiceApplication.class, args);
    }
}
