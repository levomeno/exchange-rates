package com.foreign.exchange.application.rate.service.domain.ports.output.repository;

import com.foreign.exchange.application.domain.valueobject.TransactionId;
import com.foreign.exchange.application.rate.service.domain.entity.Conversion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

public interface ConversionRepository {

    Conversion save(Conversion conversion);

    Optional<Conversion> findByTransactionId(TransactionId transactionId);

    Page<Conversion> findByTransactionIdAndTransactionDate(UUID transactionId, ZonedDateTime transactionDate, Pageable pageable);

}
