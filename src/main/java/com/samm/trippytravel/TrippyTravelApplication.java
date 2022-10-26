package com.samm.trippytravel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class TrippyTravelApplication {
    public static void main(String[] args) {
        SpringApplication.run(TrippyTravelApplication.class, args);
    }
}
