package com.foreign.exchange.application.rate.service.domain.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class ConversionRequest {

    @NotNull
    private final BigDecimal sourceAmount;
    @NotNull
    private final String sourceCurrency;
    @NotNull
    private final String targetCurrency;
    private final String date;
}
