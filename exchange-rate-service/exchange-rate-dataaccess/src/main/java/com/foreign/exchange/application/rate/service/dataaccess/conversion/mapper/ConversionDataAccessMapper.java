package com.foreign.exchange.application.rate.service.dataaccess.conversion.mapper;

import com.foreign.exchange.application.domain.valueobject.Money;
import com.foreign.exchange.application.domain.valueobject.TransactionId;
import com.foreign.exchange.application.rate.service.dataaccess.conversion.entity.ConversionEntity;
import com.foreign.exchange.application.rate.service.domain.entity.Conversion;
import org.springframework.stereotype.Component;

@Component
public class ConversionDataAccessMapper {

    public ConversionEntity conversionToConversionEntity(Conversion conversion) {
        return ConversionEntity.builder()
                .id(conversion.getId().getValue())
                .sourceCurrency(conversion.getSourceCurrency())
                .sourceAmount(conversion.getSourceAmount().getAmount())
                .targetCurrency(conversion.getTargetCurrency())
                .targetAmount(conversion.getTargetAmount().getAmount())
                .createdAt(conversion.getCreatedAt())
                .build();
    }

    public Conversion conversionEntityToConversion(ConversionEntity conversionEntity) {
        return Conversion.builder()
                .transactionId(new TransactionId(conversionEntity.getId()))
                .sourceCurrency(conversionEntity.getSourceCurrency())
                .sourceAmount(new Money(conversionEntity.getSourceAmount()))
                .targetCurrency(conversionEntity.getTargetCurrency())
                .targetAmount(new Money(conversionEntity.getTargetAmount()))
                .createdAt(conversionEntity.getCreatedAt())
                .build();
    }
}
