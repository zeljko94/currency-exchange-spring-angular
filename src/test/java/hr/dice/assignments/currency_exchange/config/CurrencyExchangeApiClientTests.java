package hr.dice.assignments.currency_exchange.config;

import hr.dice.assignments.currency_exchange.dto.ConvertAmountResponseDto;
import hr.dice.assignments.currency_exchange.dto.ExchangeRatesResponseDto;
import hr.dice.assignments.currency_exchange.properties.CurrencyApiProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CurrencyExchangeApiClientTests {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private CurrencyApiProperties currencyApiProperties;

    @InjectMocks
    private CurrencyExchangeApiClient currencyExchangeApiClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetExchangeRates() {
        // Arrange
        String base = "USD";
        String url = "https://api.example.com/exchange-rates?apiKey=test&base=USD";
        ExchangeRatesResponseDto expectedResponse = new ExchangeRatesResponseDto();
        when(currencyApiProperties.getUrl()).thenReturn("https://api.example.com/");
        when(currencyApiProperties.getExchangeRatesEndpoint()).thenReturn("exchange-rates");
        when(currencyApiProperties.getKey()).thenReturn("test");
        when(restTemplate.getForObject(url, ExchangeRatesResponseDto.class)).thenReturn(expectedResponse);

        // Act
        ExchangeRatesResponseDto result = currencyExchangeApiClient.getExchangeRates(base);

        // Assert
        assertEquals(expectedResponse, result);
        verify(restTemplate, times(1)).getForObject(url, ExchangeRatesResponseDto.class);
    }

    @Test
    void testConvertAmount() {
        // Arrange
        String base = "USD";
        String to = "EUR";
        double amount = 100;
        String url = "https://api.example.com/convert-amount?apiKey=test&base=USD&to=EUR&amount=100";
        ConvertAmountResponseDto expectedResponse = new ConvertAmountResponseDto();
        when(currencyApiProperties.getUrl()).thenReturn("https://api.example.com/");
        when(currencyApiProperties.getConvertAmountEndpoint()).thenReturn("convert-amount");
        when(currencyApiProperties.getKey()).thenReturn("test");
        when(restTemplate.getForObject(url, ConvertAmountResponseDto.class)).thenReturn(expectedResponse);

        // Act
        ConvertAmountResponseDto result = currencyExchangeApiClient.convertAmount(base, to, amount);

        // Assert
        assertEquals(expectedResponse, result);
        verify(restTemplate, times(1)).getForObject(url, ConvertAmountResponseDto.class);
    }
}
