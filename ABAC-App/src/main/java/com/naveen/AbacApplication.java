package com.naveen;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AbacApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AbacApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {

    }
}