package hr.dice.assignments.currency_exchange.services;

import hr.dice.assignments.currency_exchange.dto.CurrencyDto;
import hr.dice.assignments.currency_exchange.models.CurrencyEntity;
import hr.dice.assignments.currency_exchange.repositories.CurrencyRepository;
import hr.dice.assignments.currency_exchange.util.EntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CurrencyServiceTests {

    @Mock
    private EntityMapper mapper;

    @Mock
    private CurrencyRepository currencyRepository;

    @InjectMocks
    private CurrencyService currencyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAll() {
        // Arrange
        CurrencyEntity currency1 = new CurrencyEntity(1L, "USD", "US Dollar");
        CurrencyEntity currency2 = new CurrencyEntity(2L, "EUR", "Euro");
        when(currencyRepository.findAll()).thenReturn(Arrays.asList(currency1, currency2));
        CurrencyDto currencyDto1 = new CurrencyDto(1L, "USD", "US Dollar");
        CurrencyDto currencyDto2 = new CurrencyDto(2L, "EUR", "Euro");
        when(mapper.entityToDTO(currency1)).thenReturn(currencyDto1);
        when(mapper.entityToDTO(currency2)).thenReturn(currencyDto2);

        // Act
        List<CurrencyDto> result = currencyService.getAll();

        // Assert
        assertEquals(2, result.size());
        assertEquals(currencyDto1, result.get(0));
        assertEquals(currencyDto2, result.get(1));
        verify(currencyRepository, times(1)).findAll();
        verify(mapper, times(2)).entityToDTO((CurrencyEntity) any());
    }

    @Test
    void testGetById_ExistingId() {
        // Arrange
        Long id = 1L;
        CurrencyEntity currencyEntity = new CurrencyEntity(id, "USD", "US Dollar");
        when(currencyRepository.findById(id)).thenReturn(Optional.of(currencyEntity));
        CurrencyDto currencyDto = new CurrencyDto(id, "USD", "US Dollar");
        when(mapper.entityToDTO(currencyEntity)).thenReturn(currencyDto);

        // Act
        CurrencyDto result = currencyService.getById(id);

        // Assert
        assertEquals(currencyDto, result);
        verify(currencyRepository, times(1)).findById(id);
        verify(mapper, times(1)).entityToDTO(currencyEntity);
    }

    @Test
    void testGetById_NonExistingId() {
        // Arrange
        Long id = 1L;
        when(currencyRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        CurrencyDto result = currencyService.getById(id);

        // Assert
        assertEquals(null, result);
        verify(currencyRepository, times(1)).findById(id);
        verify(mapper, never()).entityToDTO((CurrencyEntity) any());
    }

    @Test
    void testGetByCode_ExistingCode() {
        // Arrange
        String code = "USD";
        CurrencyEntity currencyEntity = new CurrencyEntity(1L, code, "US Dollar");
        when(currencyRepository.findByCode(code)).thenReturn(Optional.of(currencyEntity));
        CurrencyDto currencyDto = new CurrencyDto(1L, code, "US Dollar");
        when(mapper.entityToDTO(currencyEntity)).thenReturn(currencyDto);

        // Act
        CurrencyDto result = currencyService.getByCode(code);

        // Assert
        assertEquals(currencyDto, result);
        verify(currencyRepository, times(1)).findByCode(code);
        verify(mapper, times(1)).entityToDTO(currencyEntity);
    }

    @Test
    void testGetByCode_NonExistingCode() {
        // Arrange
        String code = "XYZ";
        when(currencyRepository.findByCode(code)).thenReturn(Optional.empty());

        // Act
        CurrencyDto result = currencyService.getByCode(code);

        // Assert
        assertEquals(null, result);
        verify(currencyRepository, times(1)).findByCode(code);
        verify(mapper, never()).entityToDTO((CurrencyEntity) any());
    }
}
