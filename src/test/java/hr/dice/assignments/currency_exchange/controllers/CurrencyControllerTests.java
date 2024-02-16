package hr.dice.assignments.currency_exchange.controllers;

import hr.dice.assignments.currency_exchange.dto.CurrencyDto;
import hr.dice.assignments.currency_exchange.services.CurrencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class CurrencyControllerTests {

    @Mock
    private CurrencyService currencyService;

    @InjectMocks
    private CurrencyController currencyController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAll() {
        // Mocking the service method to return a list of currencies
        List<CurrencyDto> currencies = Arrays.asList(
                new CurrencyDto(1L, "USD", "US Dollar"),
                new CurrencyDto(2L, "EUR", "Euro")
        );
        when(currencyService.getAll()).thenReturn(currencies);

        // Calling the controller method
        ResponseEntity<List<CurrencyDto>> response = currencyController.getAll();

        // Verifying that the service method was called
        verify(currencyService, times(1)).getAll();

        // Asserting the response status and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(currencies, response.getBody());
    }

    @Test
    void testGetById() {
        // Mocking the service method to return a currency with ID 1
        CurrencyDto currency = new CurrencyDto(1L, "USD", "US Dollar");
        when(currencyService.getById(anyLong())).thenReturn(currency);

        // Calling the controller method with ID 1
        ResponseEntity<CurrencyDto> response = currencyController.getById(1L);

        // Verifying that the service method was called with the correct argument
        verify(currencyService, times(1)).getById(1L);

        // Asserting the response status and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(currency, response.getBody());
    }

    @Test
    void testGetByCode() {
        // Mocking the service method to return a currency with code "USD"
        CurrencyDto currency = new CurrencyDto(1L, "USD", "US Dollar");
        when(currencyService.getByCode(anyString())).thenReturn(currency);

        // Calling the controller method with code "USD"
        ResponseEntity<CurrencyDto> response = currencyController.getByCode("USD");

        // Verifying that the service method was called with the correct argument
        verify(currencyService, times(1)).getByCode("USD");

        // Asserting the response status and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(currency, response.getBody());
    }
}
