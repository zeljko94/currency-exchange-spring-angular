package hr.dice.assignments.currency_exchange.services;

import hr.dice.assignments.currency_exchange.config.CurrencyExchangeApiClient;
import hr.dice.assignments.currency_exchange.dto.ConvertAmountResponseDto;
import hr.dice.assignments.currency_exchange.dto.ExchangeRatesResponseDto;
import hr.dice.assignments.currency_exchange.models.CurrencyEntity;
import hr.dice.assignments.currency_exchange.models.ExchangeRateEntity;
import hr.dice.assignments.currency_exchange.repositories.CurrencyRepository;
import hr.dice.assignments.currency_exchange.repositories.ExchangeRateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CurrencyExchangeServiceTests {

    @Mock
    private CurrencyExchangeApiClient currencyExchangeApiClient;

    @Mock
    private ExchangeRateRepository exchangeRateRepository;

    @Mock
    private CurrencyRepository currencyRepository;

    @InjectMocks
    private CurrencyExchangeService currencyExchangeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testConvertCurrency_SameCurrency() {
        // Arrange
        String fromCurrencyCode = "USD";
        String toCurrencyCode = "USD";
        Double amount = 100.0;

        // Act
        Double result = currencyExchangeService.convertCurrency(fromCurrencyCode, toCurrencyCode, amount);

        // Assert
        assertEquals(amount, result);
    }

    @Test
    void testConvertCurrency_DifferentCurrency() {
        // Arrange
        String fromCurrencyCode = "USD";
        String toCurrencyCode = "EUR";
        Double amount = 100.0;
        Double exchangeRate = 0.85;
        ConvertAmountResponseDto responseDto = new ConvertAmountResponseDto(fromCurrencyCode, toCurrencyCode, amount, amount * exchangeRate, exchangeRate, 0);

        when(currencyExchangeApiClient.convertAmount(fromCurrencyCode, toCurrencyCode, amount)).thenReturn(responseDto);

        // Act
        Double result = currencyExchangeService.convertCurrency(fromCurrencyCode, toCurrencyCode, amount);

        // Assert
        assertEquals(amount * exchangeRate, result);
        verify(currencyExchangeApiClient, times(1)).convertAmount(fromCurrencyCode, toCurrencyCode, amount);
    }

    @Test
    void testFetchAndSaveExchangeRates() {
        // Arrange
        String baseCurrencyCode = "USD";
        ExchangeRatesResponseDto responseDto = new ExchangeRatesResponseDto(0L, baseCurrencyCode, new HashMap<>());
        Map<String, String> rates = new HashMap<>();
        rates.put("EUR", "0.85");
        rates.put("GBP", "0.72");
        responseDto.setRates(rates);

        CurrencyEntity baseCurrency = new CurrencyEntity();
        baseCurrency.setCode(baseCurrencyCode);
        baseCurrency.setName(baseCurrencyCode);
        CurrencyEntity targetCurrency = new CurrencyEntity();
        targetCurrency.setCode("EUR");
        targetCurrency.setName("Euro");

        when(currencyExchangeApiClient.getExchangeRates(baseCurrencyCode)).thenReturn(responseDto);
        when(currencyRepository.findByCode(baseCurrencyCode)).thenReturn(Optional.of(baseCurrency));
        when(currencyRepository.findByCode("EUR")).thenReturn(Optional.of(targetCurrency));
        when(exchangeRateRepository.findBySourceCurrencyAndTargetCurrency(baseCurrency, targetCurrency)).thenReturn(Optional.empty());

        // Act
        currencyExchangeService.fetchAndSaveExchangeRates(baseCurrencyCode);

        // Assert
        verify(currencyExchangeApiClient, times(1)).getExchangeRates(baseCurrencyCode);
        verify(currencyRepository, times(2)).findByCode(anyString());
        verify(exchangeRateRepository, times(2)).findBySourceCurrencyAndTargetCurrency(any(), any());
        verify(exchangeRateRepository, times(1)).save(any(ExchangeRateEntity.class));
    }
}
