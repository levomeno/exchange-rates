package com.foreign.exchange.application.rate.service.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class ConversionListRequest {
    private final UUID transactionId;
    private final String transactionDate;
}
