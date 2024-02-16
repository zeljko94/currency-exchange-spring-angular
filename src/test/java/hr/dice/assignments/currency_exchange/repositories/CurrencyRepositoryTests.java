package hr.dice.assignments.currency_exchange.repositories;

import hr.dice.assignments.currency_exchange.models.CurrencyEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CurrencyRepositoryTests {

    @Mock
    private CurrencyRepository currencyRepository;

    @Test
    void testFindByCode_WhenCodeExists() {
        // Arrange
        String code = "USD";
        CurrencyEntity currencyEntity = new CurrencyEntity();
        currencyEntity.setId(1L);
        currencyEntity.setCode(code);

        when(currencyRepository.findByCode(code)).thenReturn(Optional.of(currencyEntity));

        // Act
        Optional<CurrencyEntity> result = currencyRepository.findByCode(code);

        // Assert
        assertEquals(currencyEntity, result.orElse(null));
        verify(currencyRepository, times(1)).findByCode(code);
    }

    @Test
    void testFindByCode_WhenCodeDoesNotExist() {
        // Arrange
        String code = "XYZ";

        when(currencyRepository.findByCode(code)).thenReturn(Optional.empty());

        // Act
        Optional<CurrencyEntity> result = currencyRepository.findByCode(code);

        // Assert
        assertEquals(Optional.empty(), result);
        verify(currencyRepository, times(1)).findByCode(code);
    }
}
