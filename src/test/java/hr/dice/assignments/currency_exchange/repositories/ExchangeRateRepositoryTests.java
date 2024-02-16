package hr.dice.assignments.currency_exchange.repositories;

import hr.dice.assignments.currency_exchange.models.CurrencyEntity;
import hr.dice.assignments.currency_exchange.models.ExchangeRateEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExchangeRateRepositoryTests {

    @Mock
    private ExchangeRateRepository exchangeRateRepository;

    @Test
    void testFindBySourceCurrencyAndTargetCurrency_WhenExchangeRateExists() {
        // Arrange
        CurrencyEntity baseCurrency = new CurrencyEntity();
        CurrencyEntity targetCurrency = new CurrencyEntity();
        ExchangeRateEntity exchangeRateEntity = new ExchangeRateEntity();
        exchangeRateEntity.setId(1L);
        exchangeRateEntity.setSourceCurrency(baseCurrency);
        exchangeRateEntity.setTargetCurrency(targetCurrency);

        when(exchangeRateRepository.findBySourceCurrencyAndTargetCurrency(baseCurrency, targetCurrency))
                .thenReturn(Optional.of(exchangeRateEntity));

        // Act
        Optional<ExchangeRateEntity> result = exchangeRateRepository.findBySourceCurrencyAndTargetCurrency(baseCurrency, targetCurrency);

        // Assert
        assertEquals(exchangeRateEntity, result.orElse(null));
        verify(exchangeRateRepository, times(1)).findBySourceCurrencyAndTargetCurrency(baseCurrency, targetCurrency);
    }

    @Test
    void testFindBySourceCurrencyAndTargetCurrency_WhenExchangeRateDoesNotExist() {
        // Arrange
        CurrencyEntity baseCurrency = new CurrencyEntity();
        CurrencyEntity targetCurrency = new CurrencyEntity();

        when(exchangeRateRepository.findBySourceCurrencyAndTargetCurrency(baseCurrency, targetCurrency))
                .thenReturn(Optional.empty());

        // Act
        Optional<ExchangeRateEntity> result = exchangeRateRepository.findBySourceCurrencyAndTargetCurrency(baseCurrency, targetCurrency);

        // Assert
        assertEquals(Optional.empty(), result);
        verify(exchangeRateRepository, times(1)).findBySourceCurrencyAndTargetCurrency(baseCurrency, targetCurrency);
    }
}
