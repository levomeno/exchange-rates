package com.foreign.exchange.application.rate.service.domain;

import com.foreign.exchange.application.rate.service.domain.ports.output.repository.ConversionRepository;
import com.foreign.exchange.application.rate.service.domain.ports.output.service.ExternalExchangeRateApi;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.foreign.exchange")
public class ConversionTestConfiguration {

    @Bean
    public ConversionDomainService conversionDomainService() {
        return new ConversionDomainServiceImpl();
    }

    @Bean
    public ConversionRepository conversionRepository() {
        return Mockito.mock(ConversionRepository.class);
    }

    @Bean
    public ExternalExchangeRateApi externalExchangeRateApi() {
        return Mockito.mock(ExternalExchangeRateApiImpl.class);
    }
}
