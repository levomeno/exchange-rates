package com.foreign.exchange.application.rate.service.application;

import com.foreign.exchange.application.rate.service.application.rest.ConversionController;
import com.foreign.exchange.application.rate.service.domain.dto.ConversionRequest;
import com.foreign.exchange.application.rate.service.domain.dto.ConversionResponse;
import com.foreign.exchange.application.rate.service.domain.ports.input.service.ConversionApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

public class ConversionControllerTest {

    private ConversionController conversionController;
    private ConversionApplicationService conversionApplicationService;

    @BeforeEach
    public void setUp() {
        conversionApplicationService = Mockito.mock(ConversionApplicationService.class);
        conversionController = new ConversionController(conversionApplicationService);
    }

    @Test
    public void testConvertCurrency() {
        // Create a sample ConversionRequest
        ConversionRequest conversionRequest = ConversionRequest.builder()
                .sourceCurrency("USD")
                .targetCurrency("EUR")
                .sourceAmount(BigDecimal.valueOf(100.0))
                .build();

        // Create a sample ConversionResponse
        ConversionResponse conversionResponse = ConversionResponse.builder()
                .transactionId(UUID.randomUUID())
                .targetAmount(BigDecimal.valueOf(90.0))
                .build();

        // Mock the service response
        when(conversionApplicationService.convert(conversionRequest)).thenReturn(conversionResponse);

        // Perform the POST request to /convert
        ResponseEntity<ConversionResponse> responseEntity = conversionController.convertCurrency(conversionRequest);

        // Assertions
        assert responseEntity.getStatusCode() == HttpStatus.OK;
        assert responseEntity.getBody() != null;
        assert responseEntity.getBody().getTransactionId().equals(conversionResponse.getTransactionId());
        assert responseEntity.getBody().getTargetAmount() == conversionResponse.getTargetAmount();
    }

    @Test
    public void testGetConversionList() {
        // Create sample query parameters
        UUID transactionId = UUID.randomUUID();
        String transactionDate = "2023-08-23";
        int page = 0;
        int size = 10;

        // Create a sample list of ConversionResponse objects
        ConversionResponse response1 = ConversionResponse.builder().build();
        ConversionResponse response2 = ConversionResponse.builder().build();
        List<ConversionResponse> responseList = Arrays.asList(response1, response2);

        // Mock the service response
        when(conversionApplicationService.getConversionList(transactionId, transactionDate, PageRequest.of(page, size)))
                .thenReturn(responseList);

        // Perform the GET request to /list
        ResponseEntity<List<ConversionResponse>> responseEntity = conversionController.getConversionList(transactionId, transactionDate, page, size);

        // Assertions
        assert responseEntity.getStatusCode() == HttpStatus.OK;
        assert responseEntity.getBody() != null;
        assert responseEntity.getBody().size() == responseList.size();
        assert responseEntity.getBody().get(0) == response1;
        assert responseEntity.getBody().get(1) == response2;
    }
}
