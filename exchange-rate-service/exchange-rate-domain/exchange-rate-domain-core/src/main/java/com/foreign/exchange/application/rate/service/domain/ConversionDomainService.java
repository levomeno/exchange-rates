package com.foreign.exchange.application.rate.service.domain;

import com.foreign.exchange.application.rate.service.domain.entity.Conversion;

import java.math.BigDecimal;

public interface ConversionDomainService {

    Conversion validateAndInitiateConversion(Conversion conversion, BigDecimal rate);
}
