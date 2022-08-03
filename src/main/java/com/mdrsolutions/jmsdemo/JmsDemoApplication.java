package com.mdrsolutions.jmsdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class JmsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JmsDemoApplication.class, args);
    }
}
