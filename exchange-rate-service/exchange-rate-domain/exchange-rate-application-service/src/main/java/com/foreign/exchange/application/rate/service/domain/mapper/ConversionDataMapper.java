package com.foreign.exchange.application.rate.service.domain.mapper;

import com.foreign.exchange.application.domain.valueobject.Money;
import com.foreign.exchange.application.rate.service.domain.dto.ConversionRequest;
import com.foreign.exchange.application.rate.service.domain.dto.ConversionResponse;
import com.foreign.exchange.application.rate.service.domain.entity.Conversion;
import org.springframework.stereotype.Component;

@Component
public class ConversionDataMapper {

    public Conversion conversionRequestToConversion(ConversionRequest conversionRequest) {
        return Conversion.builder()
                .sourceAmount(new Money(conversionRequest.getSourceAmount()))
                .sourceCurrency(conversionRequest.getSourceCurrency())
                .targetCurrency(conversionRequest.getTargetCurrency())
                .build();
    }

    public ConversionResponse conversionToConversionResponse(Conversion conversion) {
        return ConversionResponse.builder()
                .sourceAmount(conversion.getSourceAmount().getAmount())
                .targetAmount(conversion.getTargetAmount().getAmount())
                .sourceCurrency(conversion.getSourceCurrency())
                .targetCurrency(conversion.getTargetCurrency())
                .transactionId(conversion.getId().getValue())
                .date(conversion.getCreatedAt().toString())
                .build();
    }
}
