package com.foreign.exchange.application.rate.service.domain.exception;

import com.foreign.exchange.application.domain.exception.DomainException;

public class ConversionNotFoundException extends DomainException {
    public ConversionNotFoundException(String message) {
        super(message);
    }

    public ConversionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
