package com.foreign.exchange.application.rate.service.domain;

import com.foreign.exchange.application.domain.valueobject.TransactionId;
import com.foreign.exchange.application.rate.service.domain.dto.ConversionRequest;
import com.foreign.exchange.application.rate.service.domain.dto.ConversionResponse;
import com.foreign.exchange.application.rate.service.domain.entity.Conversion;
import com.foreign.exchange.application.rate.service.domain.exception.ConversionDomainException;
import com.foreign.exchange.application.rate.service.domain.mapper.ConversionDataMapper;
import com.foreign.exchange.application.rate.service.domain.ports.input.service.ConversionApplicationService;
import com.foreign.exchange.application.rate.service.domain.ports.output.repository.ConversionRepository;
import com.foreign.exchange.application.rate.service.domain.valueobject.ConversionStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = ConversionTestConfiguration.class)
public class ConversionApplicationServiceTest {

    @Autowired
    private ConversionApplicationService conversionApplicationService;

    @Autowired
    private ConversionDataMapper conversionDataMapper;

    @Autowired
    private ConversionRepository conversionRepository;

    private ConversionRequest conversionRequest;

    private ConversionRequest conversionRequestWrongSourceCurrency;

    private ConversionRequest conversionRequestWrongSourceAmount;

    private final UUID TRANSACTION_ID = UUID.fromString("3e14be40-5642-4e8a-b027-5a7ca6f1acdc");

    @BeforeAll
    public void init() {
        conversionRequest = ConversionRequest.builder()
                .sourceCurrency("GBP")
                .sourceAmount(BigDecimal.valueOf(200.00))
                .targetCurrency("EUR")
                .date("2023-08-09")
                .build();

        conversionRequestWrongSourceCurrency = ConversionRequest.builder()
                .sourceCurrency("GBPP")
                .sourceAmount(BigDecimal.valueOf(200.00))
                .targetCurrency("EUR")
                .date("2023-08-09")
                .build();

        conversionRequestWrongSourceAmount = ConversionRequest.builder()
                .sourceCurrency("GBP")
                .sourceAmount(BigDecimal.valueOf(0.00))
                .targetCurrency("EUR")
                .date("2023-08-09")
                .build();

        Conversion conversion = conversionDataMapper.conversionRequestToConversion(conversionRequest);
        conversion.setId(new TransactionId(TRANSACTION_ID));

        when(conversionRepository.save(any(Conversion.class))).thenReturn(conversion);
    }

   // @Test
    public void testConversion() {
        ConversionResponse conversionResponse = conversionApplicationService.convert(conversionRequest);
        assertNotNull(conversionResponse.getTransactionId());
    }

    //@Test
    public void testConversionWithWrongSourceAmount() {
        ConversionResponse conversionResponse = conversionApplicationService.convert(conversionRequest);
        assertNotNull(conversionResponse.getTransactionId());

        ConversionDomainException conversionDomainException = assertThrows(ConversionDomainException.class,
                () -> conversionApplicationService.convert(conversionRequestWrongSourceAmount));
        assertEquals("Source amount must be greater than zero!",
                conversionDomainException.getMessage());
    }
}
