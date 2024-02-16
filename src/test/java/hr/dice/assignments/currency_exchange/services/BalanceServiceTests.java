package hr.dice.assignments.currency_exchange.services;

import hr.dice.assignments.currency_exchange.dto.BalanceDto;
import hr.dice.assignments.currency_exchange.exception.InsufficientFundsException;
import hr.dice.assignments.currency_exchange.models.BalanceEntity;
import hr.dice.assignments.currency_exchange.models.CurrencyEntity;
import hr.dice.assignments.currency_exchange.models.UserEntity;
import hr.dice.assignments.currency_exchange.properties.BalanceProperties;
import hr.dice.assignments.currency_exchange.repositories.BalanceRepository;
import hr.dice.assignments.currency_exchange.repositories.CurrencyRepository;
import hr.dice.assignments.currency_exchange.repositories.UserRepository;
import hr.dice.assignments.currency_exchange.services.CurrencyExchangeService;
import hr.dice.assignments.currency_exchange.util.EntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BalanceServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CurrencyRepository currencyRepository;

    @Mock
    private BalanceRepository balanceRepository;

    @Mock
    private CurrencyExchangeService currencyExchangeService;

    @Mock
    private EntityMapper mapper;

    @Mock
    private BalanceProperties balanceProperties;

    @InjectMocks
    private BalanceService balanceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetBalanceById() {
        // Arrange
        Long balanceId = 1L;
        BalanceEntity balanceEntity = new BalanceEntity();
        balanceEntity.setId(balanceId);
        BalanceDto expectedBalanceDto = new BalanceDto(balanceEntity.getId(), "Test Balance", "USD", 1L, 100.0);

        when(balanceRepository.findById(balanceId)).thenReturn(Optional.of(balanceEntity));
        when(mapper.entityToDTO(balanceEntity)).thenReturn(expectedBalanceDto);

        // Act
        BalanceDto result = balanceService.getBalanceById(balanceId);

        // Assert
        assertEquals(expectedBalanceDto, result);
        verify(balanceRepository, times(1)).findById(balanceId);
        verify(mapper, times(1)).entityToDTO(balanceEntity);
    }

    @Test
    void testAddMoney() {
        // Arrange
        Long balanceId = 1L;
        Double amountToAdd = 50.0;
        BalanceEntity balanceEntity = new BalanceEntity();
        balanceEntity.setId(balanceId);
        balanceEntity.setAmount(100.0);
        BalanceDto expectedBalanceDto = new BalanceDto(balanceEntity.getId(), "Test Balance", "USD", 1L, 150.0);

        when(balanceRepository.findById(balanceId)).thenReturn(Optional.of(balanceEntity));
        when(balanceRepository.save(any(BalanceEntity.class))).thenReturn(balanceEntity);
        when(mapper.entityToDTO(balanceEntity)).thenReturn(expectedBalanceDto);

        // Act
        BalanceDto result = balanceService.addMoney(balanceId, amountToAdd);

        // Assert
        assertEquals(expectedBalanceDto, result);
        assertEquals(150.0, balanceEntity.getAmount());
        verify(balanceRepository, times(1)).findById(balanceId);
        verify(balanceRepository, times(1)).save(balanceEntity);
        verify(mapper, times(1)).entityToDTO(balanceEntity);
    }

    @Test
    void testSubtractMoney_InsufficientFundsException() {
        // Arrange
        Long balanceId = 1L;
        Double amountToSubtract = 150.0;
        BalanceEntity balanceEntity = new BalanceEntity();
        balanceEntity.setId(balanceId);
        balanceEntity.setAmount(100.0);

        when(balanceRepository.findById(balanceId)).thenReturn(Optional.of(balanceEntity));
        when(balanceProperties.isAllowSubtractWithoutFunds()).thenReturn(false);

        // Act & Assert
        assertThrows(InsufficientFundsException.class, () -> balanceService.subtractMoney(balanceId, amountToSubtract));
        verify(balanceRepository, times(1)).findById(balanceId);
        verify(balanceProperties, times(1)).isAllowSubtractWithoutFunds();
        verify(balanceRepository, never()).save(any(BalanceEntity.class));
        verify(mapper, never()).entityToDTO(any(BalanceEntity.class));
    }

    @Test
    void testSubtractMoney_Successful() {
        // Arrange
        Long balanceId = 1L;
        Double amountToSubtract = 50.0;
        BalanceEntity balanceEntity = new BalanceEntity();
        balanceEntity.setId(balanceId);
        balanceEntity.setAmount(100.0);
        BalanceDto expectedBalanceDto = new BalanceDto(balanceEntity.getId(), "Test Balance", "USD", 1L, 50.0);

        when(balanceRepository.findById(balanceId)).thenReturn(Optional.of(balanceEntity));
        when(balanceProperties.isAllowSubtractWithoutFunds()).thenReturn(true);
        when(balanceRepository.save(any(BalanceEntity.class))).thenReturn(balanceEntity);
        when(mapper.entityToDTO(balanceEntity)).thenReturn(expectedBalanceDto);

        // Act
        BalanceDto result = balanceService.subtractMoney(balanceId, amountToSubtract);

        // Assert
        assertEquals(expectedBalanceDto, result);
        assertEquals(50.0, balanceEntity.getAmount());
        verify(balanceRepository, times(1)).findById(balanceId);
        verify(balanceProperties, times(1)).isAllowSubtractWithoutFunds();
        verify(balanceRepository, times(1)).save(balanceEntity);
        verify(mapper, times(1)).entityToDTO(balanceEntity);
    }

    // Add more tests for other methods similarly
}
