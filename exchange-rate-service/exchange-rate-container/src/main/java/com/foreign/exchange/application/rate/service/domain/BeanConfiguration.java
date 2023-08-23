package com.foreign.exchange.application.rate.service.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfiguration {

    @Bean
    public ConversionDomainService conversionDomainService() {
        return new ConversionDomainServiceImpl();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
