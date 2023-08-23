package com.foreign.exchange.application.rate.service.application.rest;

import com.foreign.exchange.application.rate.service.domain.dto.ExchangeRateResponse;
import com.foreign.exchange.application.rate.service.domain.ports.output.service.ExternalExchangeRateApi;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/exchange-rates")
public class ExchangeRateController {

    private final ExternalExchangeRateApi externalExchangeRateApi;

    public ExchangeRateController(ExternalExchangeRateApi externalExchangeRateApi) {
        this.externalExchangeRateApi = externalExchangeRateApi;
    }

    @GetMapping("/{sourceCurrency}/{targetCurrency}")
    public ResponseEntity<ExchangeRateResponse> getExchangeRate(
            @PathVariable @NotBlank @Size(max = 3, min = 3) String sourceCurrency,
            @PathVariable @NotBlank @Size(max = 3, min = 3) String targetCurrency) {
        Map<String, BigDecimal> rates = externalExchangeRateApi.getExchangeRates(null,
                sourceCurrency,
                targetCurrency);
        ExchangeRateResponse response = new ExchangeRateResponse(sourceCurrency, targetCurrency, rates.get(targetCurrency));
        return ResponseEntity.ok(response);
    }
}
