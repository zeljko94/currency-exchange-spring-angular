package hr.dice.assignments.currency_exchange.controllers;

import hr.dice.assignments.currency_exchange.dto.ConvertAmountResponseDto;
import hr.dice.assignments.currency_exchange.dto.ExchangeRatesResponseDto;
import hr.dice.assignments.currency_exchange.services.CurrencyExchangeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ExchangeRatesControllerTests {

    @Mock
    private CurrencyExchangeService currencyExchangeService;

    @InjectMocks
    private ExchangeRatesController exchangeRatesController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetExchangeRates() {
        // Arrange
        ExchangeRatesResponseDto responseDto = new ExchangeRatesResponseDto(123456789L, "USD", new HashMap<>());
        when(currencyExchangeService.getExchangeRates(anyString())).thenReturn(responseDto);

        // Act
        ResponseEntity<ExchangeRatesResponseDto> response = exchangeRatesController.getExchangeRates("USD");

        // Assert
        verify(currencyExchangeService, times(1)).getExchangeRates("USD");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }

    @Test
    void testConvertAmount() {
        // Arrange
        ConvertAmountResponseDto responseDto = new ConvertAmountResponseDto("USD", "EUR", 100.0, 80.0, 0.8, 123456789L);
        when(currencyExchangeService.convertAmount(anyString(), anyString(), anyDouble())).thenReturn(responseDto);

        // Act
        ResponseEntity<ConvertAmountResponseDto> response = exchangeRatesController.convertAmount("USD", "EUR", 100.0);

        // Assert
        verify(currencyExchangeService, times(1)).convertAmount("USD", "EUR", 100.0);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }

    @Test
    void testTestFetchRates() {
        // Act
        ResponseEntity<HttpStatus> response = exchangeRatesController.testFetchRates();

        // Assert
        verify(currencyExchangeService, times(1)).fetchAndSaveExchangeRates("EUR");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
