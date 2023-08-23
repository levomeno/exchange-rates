package com.foreign.exchange.application.rate.service.domain;

import com.foreign.exchange.application.rate.service.domain.dto.ConversionRequest;
import com.foreign.exchange.application.rate.service.domain.entity.Conversion;
import com.foreign.exchange.application.rate.service.domain.exception.ConversionDomainException;
import com.foreign.exchange.application.rate.service.domain.mapper.ConversionDataMapper;
import com.foreign.exchange.application.rate.service.domain.ports.output.repository.ConversionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Component
public class ConversionHelper {

    private final ConversionDomainService conversionDomainService;
    private final ConversionRepository conversionRepository;
    private final ConversionDataMapper conversionDataMapper;

    public ConversionHelper(ConversionDomainService conversionDomainService,
                            ConversionRepository conversionRepository,
                            ConversionDataMapper conversionDataMapper) {
        this.conversionDomainService = conversionDomainService;
        this.conversionRepository = conversionRepository;
        this.conversionDataMapper = conversionDataMapper;
    }

    @Transactional
    public Conversion persistConversion(ConversionRequest conversionRequest, BigDecimal rate) {
        Conversion conversion = conversionDataMapper.conversionRequestToConversion(conversionRequest);
        conversion = conversionDomainService.validateAndInitiateConversion(conversion, rate);
        Conversion conversionResult = saveConversion(conversion);
        log.info("Conversion is created with id: {}", conversionResult.getId().getValue());
        return conversionResult;
    }

    private Conversion saveConversion(Conversion conversion) {
        Conversion conversionResult = conversionRepository.save(conversion);
        if (conversionResult == null) {
            log.error("Could not save conversion!");
            throw new ConversionDomainException("Could not save conversion!");
        }
        log.info("Conversion is saved with id: {}", conversionResult.getId().getValue());
        return conversionResult;
    }
}
