package com.foreign.exchange.application.rate.service.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class ExchangeRateResponse {

    private String sourceCurrency;
    private String targetCurrency;
    private BigDecimal rate;
}
