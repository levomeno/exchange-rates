package com.foreign.exchange.application.rate.service.domain;

import com.foreign.exchange.application.rate.service.domain.dto.ConversionRequest;
import com.foreign.exchange.application.rate.service.domain.dto.ConversionResponse;
import com.foreign.exchange.application.rate.service.domain.entity.Conversion;
import com.foreign.exchange.application.rate.service.domain.exception.ConversionDomainException;
import com.foreign.exchange.application.rate.service.domain.mapper.ConversionDataMapper;
import com.foreign.exchange.application.rate.service.domain.ports.output.service.ExternalExchangeRateApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Slf4j
@Component
public class ConversionHandler {
    private final ConversionHelper conversionHelper;
    private final ExternalExchangeRateApi externalExchangeRateApi;
    private final ConversionDataMapper conversionDataMapper;

    public ConversionHandler(ConversionHelper conversionHelper,
                             ExternalExchangeRateApi externalExchangeRateApi,
                             ConversionDataMapper conversionDataMapper) {
        this.conversionHelper = conversionHelper;
        this.externalExchangeRateApi = externalExchangeRateApi;
        this.conversionDataMapper = conversionDataMapper;
    }

    public ConversionResponse convert(ConversionRequest conversionRequest) {
        Map<String, BigDecimal> rates = externalExchangeRateApi.getExchangeRates(null,
                conversionRequest.getSourceCurrency(),
                conversionRequest.getTargetCurrency());
        if (rates == null) {
            log.error("Could not get rates!");
            throw new ConversionDomainException("Could not get rates!");
        }
        Conversion conversion = conversionHelper.persistConversion(conversionRequest,
                rates.get(conversionRequest.getTargetCurrency()));
        return conversionDataMapper.conversionToConversionResponse(conversion);
    }
}
