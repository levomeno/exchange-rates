package com.foreign.exchange.application.rate.service.domain.ports.input.service;

import com.foreign.exchange.application.rate.service.domain.dto.ConversionRequest;
import com.foreign.exchange.application.rate.service.domain.dto.ConversionResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ConversionApplicationService {

    ConversionResponse convert(@Valid ConversionRequest conversionRequest);

    List<ConversionResponse> getConversionList(UUID transactionId, String transactionDate, Pageable paging);
}
