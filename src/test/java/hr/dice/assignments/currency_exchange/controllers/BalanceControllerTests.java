package hr.dice.assignments.currency_exchange.controllers;

import hr.dice.assignments.currency_exchange.dto.BalanceDto;
import hr.dice.assignments.currency_exchange.services.BalanceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class BalanceControllerTests {

    @Mock
    private BalanceService balanceService;

    @InjectMocks
    private BalanceController balanceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetBalance() {
        // Arrange
        BalanceDto balanceDto = new BalanceDto(1L, "Test Balance", "USD", 1L, 100.0);
        when(balanceService.getBalanceById(anyLong())).thenReturn(balanceDto);

        // Act
        ResponseEntity<BalanceDto> response = balanceController.getBalance(1L);

        // Assert
        verify(balanceService, times(1)).getBalanceById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(balanceDto, response.getBody());
    }

    @Test
    void testInsertBalance() {
        // Arrange
        BalanceDto newBalanceDto = new BalanceDto(null, "New Balance", "USD", 1L, 200.0);
        Long userId = 1L;
        when(balanceService.insert(newBalanceDto, userId)).thenReturn(newBalanceDto);

        // Act
        ResponseEntity<BalanceDto> response = balanceController.insertBalance(newBalanceDto, userId);

        // Assert
        verify(balanceService, times(1)).insert(newBalanceDto, userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(newBalanceDto, response.getBody());
    }

    @Test
    void testAddMoneyToBalance() {
        // Arrange
        Long balanceId = 1L;
        Double amount = 50.0;
        BalanceDto updatedBalance = new BalanceDto(balanceId, "Test Balance", "USD", 1L, 150.0);
        when(balanceService.addMoney(balanceId, amount)).thenReturn(updatedBalance);

        // Act
        ResponseEntity<BalanceDto> response = balanceController.addMoneyToBalance(balanceId, amount);

        // Assert
        verify(balanceService, times(1)).addMoney(balanceId, amount);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedBalance, response.getBody());
    }

    @Test
    void testSubtractMoneyFromBalance() {
        // Arrange
        Long balanceId = 1L;
        Double amount = 50.0;
        BalanceDto updatedBalance = new BalanceDto(balanceId, "Test Balance", "USD", 1L, 50.0);
        when(balanceService.subtractMoney(balanceId, amount)).thenReturn(updatedBalance);

        // Act
        ResponseEntity<BalanceDto> response = balanceController.subtractMoneyFromBalance(balanceId, amount);

        // Assert
        verify(balanceService, times(1)).subtractMoney(balanceId, amount);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedBalance, response.getBody());
    }

    @Test
    void testTransferMoneyFromBalance() {
        // Arrange
        Long balanceFromId = 1L;
        Long balanceToId = 2L;
        Double amount = 50.0;
        BalanceDto updatedBalance = new BalanceDto(balanceToId, "Recipient Balance", "USD", 2L, 50.0);
        when(balanceService.transferMoney(balanceFromId, balanceToId, amount)).thenReturn(updatedBalance);

        // Act
        ResponseEntity<BalanceDto> response = balanceController.transferMoneyFromBalance(balanceFromId, balanceToId, amount);

        // Assert
        verify(balanceService, times(1)).transferMoney(balanceFromId, balanceToId, amount);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedBalance, response.getBody());
    }

    @Test
    void testGetBalancesForUser() {
        // Arrange
        Long userId = 1L;
        List<BalanceDto> balanceDtoList = new ArrayList<>();
        balanceDtoList.add(new BalanceDto(1L, "Test Balance 1", "USD", userId, 100.0));
        balanceDtoList.add(new BalanceDto(2L, "Test Balance 2", "EUR", userId, 200.0));
        when(balanceService.getBalancesForUser(userId)).thenReturn(balanceDtoList);

        // Act
        ResponseEntity<List<BalanceDto>> response = balanceController.getBalancesForUser(userId);

        // Assert
        verify(balanceService, times(1)).getBalancesForUser(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(balanceDtoList, response.getBody());
    }
}
