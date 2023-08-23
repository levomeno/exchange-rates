package com.foreign.exchange.application.rate.service.domain.ports.output.service;

import java.math.BigDecimal;
import java.util.Map;

public interface ExternalExchangeRateApi {

    Map<String, BigDecimal> getExchangeRates(String date,
                                             String sourceCurrency,
                                             String... targetCurrencies);
}
