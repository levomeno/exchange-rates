package com.foreign.exchange.application.rate.service.application.rest;

import com.foreign.exchange.application.rate.service.domain.dto.ConversionRequest;
import com.foreign.exchange.application.rate.service.domain.dto.ConversionResponse;
import com.foreign.exchange.application.rate.service.domain.ports.input.service.ConversionApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/conversions", produces = "application/vnd.api.v1+json")
public class ConversionController {

    private final ConversionApplicationService conversionApplicationService;

    public ConversionController(ConversionApplicationService conversionApplicationService) {
        this.conversionApplicationService = conversionApplicationService;
    }

    @PostMapping("/convert")
    public ResponseEntity<ConversionResponse> convertCurrency(@RequestBody ConversionRequest conversionRequest) {
        log.info("Conversion from: {} to: {} for -> {}",
                conversionRequest.getSourceCurrency(),
                conversionRequest.getTargetCurrency(),
                conversionRequest.getSourceAmount());
        ConversionResponse conversionResponse = conversionApplicationService.convert(conversionRequest);
        log.info("Conversion done with transaction id: {}, target amount is -> {}",
                conversionResponse.getTransactionId(),
                conversionResponse.getTargetAmount());
        return ResponseEntity.ok(conversionResponse);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ConversionResponse>> getConversionList(
            @RequestParam(name = "transactionId", required = false) UUID transactionId,
            @RequestParam(name = "transactionDate", required = false) String transactionDate,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        List<ConversionResponse> responseList = conversionApplicationService.getConversionList(transactionId, transactionDate, PageRequest.of(page, size));
        return ResponseEntity.ok(responseList);
    }
}
