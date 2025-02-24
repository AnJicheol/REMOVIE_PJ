package com.example.removie_read_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableCaching
@SpringBootApplication
public class RemovieReadServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RemovieReadServerApplication.class, args);
    }

}
