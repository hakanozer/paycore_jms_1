package com.works;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class ConsumerMessage1Application {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerMessage1Application.class, args);
    }

}
