package hr.dice.assignments.currency_exchange.config;


import hr.dice.assignments.currency_exchange.dto.ConvertAmountResponseDto;
import hr.dice.assignments.currency_exchange.dto.ExchangeRatesResponseDto;
import hr.dice.assignments.currency_exchange.properties.CurrencyApiProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Component class for interacting with a third-party currency exchange API.
 */
@Component
public class CurrencyExchangeApiClient implements hr.dice.assignments.currency_exchange.config.interfaces.CurrencyExchangeApiClient {
    private final RestTemplate restTemplate;
    private final CurrencyApiProperties currencyApiProperties;

    /**
     * Constructor for creating an instance of the CurrencyExchangeApiClient.
     *
     * @param restTemplate RestTemplate bean for making HTTP requests.
     */
    @Autowired
    public CurrencyExchangeApiClient(RestTemplate restTemplate, CurrencyApiProperties currencyApiProperties) {
        this.restTemplate = restTemplate;
        this.currencyApiProperties = currencyApiProperties;
    }

    /**
     * Retrieves exchange rates for a specified base currency from the API.
     *
     * @param base The base currency code.
     * @return ExchangeRatesResponseDto containing exchange rates.
     */
    public ExchangeRatesResponseDto getExchangeRates(String base) {
        String url = currencyApiProperties.getUrl() + currencyApiProperties.getExchangeRatesEndpoint() + "?apiKey=" + currencyApiProperties.getKey() + "&base=" + base;
        return restTemplate.getForObject(url, ExchangeRatesResponseDto.class);
    }

    /**
     * Converts an amount from one currency to another using the API.
     *
     * @param base   The base currency code.
     * @param to     The target currency code.
     * @param amount The amount to convert.
     * @return ConvertAmountResponseDto containing the converted amount.
     */
    public ConvertAmountResponseDto convertAmount(String base, String to, double amount) {
        String url = currencyApiProperties.getUrl() + currencyApiProperties.getConvertAmountEndpoint() + "?apiKey=" + currencyApiProperties.getKey() + "&base=" + base + "&to=" + to + "&amount=" + amount;
        return restTemplate.getForObject(url, ConvertAmountResponseDto.class);
    }
}
