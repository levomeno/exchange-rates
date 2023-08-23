package com.foreign.exchange.application.rate.service.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ExchangeRateApiRequest {

    private String accessKey; // Your API Key
    private String base; // Optional: Base currency code
    private String symbols; // Optional: Comma-separated list of currency codes
}
