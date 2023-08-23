package com.foreign.exchange.application.rate.service.domain;

import com.foreign.exchange.application.rate.service.domain.entity.Conversion;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class ConversionDomainServiceImpl implements ConversionDomainService{

    @Override
    public Conversion validateAndInitiateConversion(Conversion conversion, BigDecimal rate) {
        conversion.validateConversion();
        conversion.calculateTargetAmount(rate);
        conversion.initializeConversion();
        return conversion;
    }
}
