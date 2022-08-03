package com.mdrsolutions.jmsdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class JmsDemoApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(JmsDemoApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(JmsDemoApplication.class, args);
    }
}
