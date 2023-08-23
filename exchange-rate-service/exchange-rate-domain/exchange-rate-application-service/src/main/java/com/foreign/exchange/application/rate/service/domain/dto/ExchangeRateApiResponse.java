package com.foreign.exchange.application.rate.service.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateApiResponse {

    private boolean success;
    private long timestamp;
    private boolean historical;
    private String base;
    private String date;
    private Map<String, BigDecimal> rates;
}
