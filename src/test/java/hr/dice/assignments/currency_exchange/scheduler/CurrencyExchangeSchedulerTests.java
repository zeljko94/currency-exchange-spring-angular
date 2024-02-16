package hr.dice.assignments.currency_exchange.scheduler;

import hr.dice.assignments.currency_exchange.scheduler.CurrencyExchangeScheduler;
import hr.dice.assignments.currency_exchange.services.CurrencyExchangeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class CurrencyExchangeSchedulerTests {

    @Mock
    private CurrencyExchangeService currencyExchangeService;

    @InjectMocks
    private CurrencyExchangeScheduler currencyExchangeScheduler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFetchAndStoreExchangeRates() {
        // Arrange

        // Act
        currencyExchangeScheduler.fetchAndStoreExchangeRates();

        // Assert
        verify(currencyExchangeService, times(1)).fetchAndSaveExchangeRates("EUR");
    }
}
