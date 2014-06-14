package com.ekino.technoshare.rabbitmq;

import com.ekino.technoshare.rabbitmq.config.GsonConfig;
import com.ekino.technoshare.rabbitmq.config.RabbitMQConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {
    "com.ekino.technoshare.rabbitmq.filter",
    "com.ekino.technoshare.rabbitmq.repository",
    "com.ekino.technoshare.rabbitmq.service",
    "com.ekino.technoshare.rabbitmq.controller"
})
@Import({
        GsonConfig.class,
        RabbitMQConfig.class
})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}