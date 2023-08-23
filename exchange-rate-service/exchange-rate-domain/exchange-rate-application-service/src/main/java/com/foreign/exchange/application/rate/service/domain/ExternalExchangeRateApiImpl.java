package com.foreign.exchange.application.rate.service.domain;

import com.foreign.exchange.application.rate.service.domain.dto.ExchangeRateApiResponse;
import com.foreign.exchange.application.rate.service.domain.ports.output.service.ExternalExchangeRateApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@Component
@Slf4j
public class ExternalExchangeRateApiImpl implements ExternalExchangeRateApi {

    @Value("${API_KEY}")
    private String apiKey;
    private final RestTemplate restTemplate;
    private static final String EXTERNAL_API_URL = "http://data.fixer.io/api/";

    public ExternalExchangeRateApiImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Map<String, BigDecimal> getExchangeRates(String date, String sourceCurrency, String... targetCurrencies) {
        String apiUrl = buildApiUrl(date, sourceCurrency, targetCurrencies);

        ExchangeRateApiResponse response = restTemplate.getForObject(apiUrl, ExchangeRateApiResponse.class);

        if (response != null) {
            return response.getRates();
        }

        return null;
    }

    private String buildApiUrl(String date, String sourceCurrency, String... targetCurrencies) {
        StringBuilder apiUrlBuilder = new StringBuilder(EXTERNAL_API_URL);

        if (date != null && !date.isEmpty()) {
            apiUrlBuilder.append(date);
        } else {
            apiUrlBuilder.append("latest");
        }

        apiUrlBuilder.append("?access_key=" + apiKey); // Replace with your API key
        apiUrlBuilder.append("&base=").append(sourceCurrency);
        apiUrlBuilder.append("&symbols=").append(String.join(",", targetCurrencies));

        return apiUrlBuilder.toString();
    }
}
