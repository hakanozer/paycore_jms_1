package com.works;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class ConsumerMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerMessageApplication.class, args);
    }

}
