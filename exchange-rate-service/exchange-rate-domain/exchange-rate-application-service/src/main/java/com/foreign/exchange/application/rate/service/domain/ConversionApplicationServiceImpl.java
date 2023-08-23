package com.foreign.exchange.application.rate.service.domain;

import com.foreign.exchange.application.rate.service.domain.dto.ConversionRequest;
import com.foreign.exchange.application.rate.service.domain.dto.ConversionResponse;
import com.foreign.exchange.application.rate.service.domain.ports.input.service.ConversionApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Slf4j
@Validated
@Service
public class ConversionApplicationServiceImpl implements ConversionApplicationService {

    private final ConversionHandler conversionHandler;
    private final ConversionListHandler conversionListHandler;

    public ConversionApplicationServiceImpl(ConversionHandler conversionHandler,
                                            ConversionListHandler conversionListHandler) {
        this.conversionHandler = conversionHandler;
        this.conversionListHandler = conversionListHandler;
    }

    @Override
    public ConversionResponse convert(ConversionRequest conversionRequest) {
        return conversionHandler.convert(conversionRequest);
    }

    @Override
    public List<ConversionResponse> getConversionList(UUID transactionId, String transactionDate, Pageable paging) {
        return conversionListHandler.getConversionList(transactionId, transactionDate, paging);
    }
}
