package com.works;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class ConsumerBasket2Application {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerBasket2Application.class, args);
    }

}
