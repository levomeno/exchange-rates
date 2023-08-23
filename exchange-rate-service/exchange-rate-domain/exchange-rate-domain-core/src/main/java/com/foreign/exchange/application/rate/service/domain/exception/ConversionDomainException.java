package com.foreign.exchange.application.rate.service.domain.exception;

import com.foreign.exchange.application.domain.exception.DomainException;

public class ConversionDomainException extends DomainException {
    public ConversionDomainException(String message) {
        super(message);
    }

    public ConversionDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
