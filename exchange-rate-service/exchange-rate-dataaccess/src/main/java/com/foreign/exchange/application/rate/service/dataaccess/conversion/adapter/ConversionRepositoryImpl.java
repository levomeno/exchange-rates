package com.foreign.exchange.application.rate.service.dataaccess.conversion.adapter;

import com.foreign.exchange.application.domain.valueobject.TransactionId;
import com.foreign.exchange.application.rate.service.dataaccess.conversion.mapper.ConversionDataAccessMapper;
import com.foreign.exchange.application.rate.service.dataaccess.conversion.repository.ConversionJpaRepository;
import com.foreign.exchange.application.rate.service.domain.entity.Conversion;
import com.foreign.exchange.application.rate.service.domain.ports.output.repository.ConversionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

@Component
public class ConversionRepositoryImpl implements ConversionRepository {

    private final ConversionJpaRepository conversionJpaRepository;

    private final ConversionDataAccessMapper conversionDataAccessMapper;

    public ConversionRepositoryImpl(ConversionJpaRepository conversionJpaRepository,
                                    ConversionDataAccessMapper conversionDataAccessMapper) {
        this.conversionJpaRepository = conversionJpaRepository;
        this.conversionDataAccessMapper = conversionDataAccessMapper;
    }

    @Override
    public Conversion save(Conversion conversion) {
        return conversionDataAccessMapper.conversionEntityToConversion(conversionJpaRepository
                .save(conversionDataAccessMapper.conversionToConversionEntity(conversion)));
    }

    @Override
    public Optional<Conversion> findByTransactionId(TransactionId transactionId) {
        return conversionJpaRepository.findById(transactionId.getValue())
                .map(conversionDataAccessMapper::conversionEntityToConversion);
    }

    @Override
    public Page<Conversion> findByTransactionIdAndTransactionDate(UUID transactionId, ZonedDateTime transactionDate, Pageable pageable) {
        ZonedDateTime endOfDay = null;
        if (transactionDate != null) {
            endOfDay = transactionDate.plusDays(1);
        }
        return conversionJpaRepository.findByIdAndCreatedAt(transactionId, transactionDate, endOfDay ,pageable)
                .map(conversionDataAccessMapper::conversionEntityToConversion);
    }
}
