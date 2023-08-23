package com.foreign.exchange.application.rate.service.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class ConversionResponse {

    @NotNull
    private UUID transactionId;
    @NotNull
    private BigDecimal sourceAmount;
    @NotNull
    private String sourceCurrency;
    @NotNull
    private BigDecimal targetAmount;
    @NotNull
    private String targetCurrency;
    @NotNull
    private String date;
}
