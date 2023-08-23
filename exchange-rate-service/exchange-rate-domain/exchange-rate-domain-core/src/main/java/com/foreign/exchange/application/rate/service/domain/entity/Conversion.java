package com.foreign.exchange.application.rate.service.domain.entity;

import com.foreign.exchange.application.domain.entity.AggregateRoot;
import com.foreign.exchange.application.domain.valueobject.Money;
import com.foreign.exchange.application.domain.valueobject.TransactionId;
import com.foreign.exchange.application.rate.service.domain.exception.ConversionDomainException;
import com.foreign.exchange.application.rate.service.domain.valueobject.ConversionStatus;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

public class Conversion extends AggregateRoot<TransactionId> {

    private final String sourceCurrency;
    private final String targetCurrency;
    private final Money sourceAmount;
    private Money targetAmount;
    private ConversionStatus conversionStatus;
    private ZonedDateTime createdAt;

    public void initializeConversion() {
        setId(new TransactionId(UUID.randomUUID()));
        conversionStatus = ConversionStatus.PENDING;
        createdAt = ZonedDateTime.now(ZoneId.of("UTC"));
    }

    public void validateSourceAmount() {
        if (sourceAmount == null || !sourceAmount.isGreaterThanZero()) {
           // failureMessages.add("Source amount must be greater than zero!");
            throw new ConversionDomainException("Source amount must be greater than zero!");
        }
    }

    public void validateConversion() {
        validateInitialOrder();
        validateSourceAmount();
    }

    private void validateInitialOrder() {
        if (conversionStatus != null || getId() != null) {
            throw new ConversionDomainException("Conversion is not in correct state for initialization!");
        }
    }

    public void updateStatus(ConversionStatus conversionStatus) {
        this.conversionStatus = conversionStatus;
    }

    public void calculateTargetAmount(BigDecimal exchangeRate) {
        if (!sourceAmount.isGreaterThanZero()) {
            throw new ConversionDomainException("Source amount must be greater than zero!");
        }
        if (exchangeRate.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ConversionDomainException("Exchange rate must be positive!");
        }
        targetAmount = sourceAmount.multiply(exchangeRate);
    }

    private Conversion(Builder builder) {
        super.setId(builder.transactionId);
        sourceCurrency = builder.sourceCurrency;
        targetCurrency = builder.targetCurrency;
        sourceAmount = builder.sourceAmount;
        targetAmount = builder.targetAmount;
        conversionStatus = builder.conversionStatus;
        createdAt = builder.createdAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getSourceCurrency() {
        return sourceCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public Money getSourceAmount() {
        return sourceAmount;
    }

    public Money getTargetAmount() {
        return targetAmount;
    }

    public ConversionStatus getConversionStatus() {
        return conversionStatus;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public static final class Builder {
        private TransactionId transactionId;
        private String sourceCurrency;
        private String targetCurrency;
        private Money sourceAmount;
        private Money targetAmount;
        private ConversionStatus conversionStatus;
        private ZonedDateTime createdAt;

        private Builder() {
        }

        public Builder transactionId(TransactionId val) {
            transactionId = val;
            return this;
        }

        public Builder sourceCurrency(String val) {
            sourceCurrency = val;
            return this;
        }

        public Builder targetCurrency(String val) {
            targetCurrency = val;
            return this;
        }

        public Builder sourceAmount(Money val) {
            sourceAmount = val;
            return this;
        }

        public Builder targetAmount(Money val) {
            targetAmount = val;
            return this;
        }

        public Builder conversionStatus(ConversionStatus val) {
            conversionStatus = val;
            return this;
        }

        public Builder createdAt(ZonedDateTime val) {
            createdAt = val;
            return this;
        }

        public Conversion build() {
            return new Conversion(this);
        }
    }
}
