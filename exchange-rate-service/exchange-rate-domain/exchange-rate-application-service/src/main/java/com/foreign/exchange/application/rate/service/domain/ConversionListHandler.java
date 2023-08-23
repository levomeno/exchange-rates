package com.foreign.exchange.application.rate.service.domain;

import com.foreign.exchange.application.rate.service.domain.dto.ConversionResponse;
import com.foreign.exchange.application.rate.service.domain.entity.Conversion;
import com.foreign.exchange.application.rate.service.domain.mapper.ConversionDataMapper;
import com.foreign.exchange.application.rate.service.domain.ports.output.repository.ConversionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class ConversionListHandler {

    private final ConversionRepository conversionRepository;
    private final ConversionDataMapper conversionDataMapper;


    public ConversionListHandler(ConversionRepository conversionRepository,
                                 ConversionDataMapper conversionDataMapper) {
        this.conversionRepository = conversionRepository;
        this.conversionDataMapper = conversionDataMapper;
    }

    @Transactional(readOnly = true)
    public List<ConversionResponse> getConversionList(UUID transactionId, String transactionDate, Pageable paging) {
        ZonedDateTime zonedDateTime = null;
        if (transactionDate != null) {
            // Define the date format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // Parse the string to LocalDate
            LocalDate localDate = LocalDate.parse(transactionDate, formatter);

            // Convert LocalDate to ZonedDateTime with a specific time zone
            ZoneId zoneId = ZoneId.of("UTC"); // Replace with your desired time zone
            zonedDateTime = localDate.atStartOfDay(zoneId);
        }

        Page<Conversion> conversionPage = conversionRepository
                .findByTransactionIdAndTransactionDate(transactionId, zonedDateTime, paging);
        return conversionPage.getContent().stream().map(conversionDataMapper::conversionToConversionResponse).toList();
    }
}
