package hr.dice.assignments.currency_exchange.repositories;

import hr.dice.assignments.currency_exchange.models.BalanceEntity;
import hr.dice.assignments.currency_exchange.models.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BalanceRepositoryTests {

    @Mock
    private BalanceRepository balanceRepository;

    @Test
    void testFindByUser() {
        // Arrange
        UserEntity user = new UserEntity();
        user.setId(1L);

        BalanceEntity balance1 = new BalanceEntity();
        balance1.setId(1L);
        balance1.setUser(user);

        BalanceEntity balance2 = new BalanceEntity();
        balance2.setId(2L);
        balance2.setUser(user);

        List<BalanceEntity> expectedBalances = new ArrayList<>();
        expectedBalances.add(balance1);
        expectedBalances.add(balance2);

        when(balanceRepository.findByUser(user)).thenReturn(expectedBalances);

        // Act
        List<BalanceEntity> actualBalances = balanceRepository.findByUser(user);

        // Assert
        assertEquals(expectedBalances.size(), actualBalances.size());
        for (int i = 0; i < expectedBalances.size(); i++) {
            assertEquals(expectedBalances.get(i).getId(), actualBalances.get(i).getId());
        }
        verify(balanceRepository, times(1)).findByUser(user);
    }
}
