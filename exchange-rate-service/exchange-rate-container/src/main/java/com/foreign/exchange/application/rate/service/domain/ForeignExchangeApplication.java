package com.foreign.exchange.application.rate.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.foreign.exchange.application.rate.service.dataaccess")
@EntityScan(basePackages = "com.foreign.exchange.application.rate.service.dataaccess")
@SpringBootApplication(scanBasePackages = "com.foreign.exchange.application")
public class ForeignExchangeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForeignExchangeApplication.class, args);
    }
}
