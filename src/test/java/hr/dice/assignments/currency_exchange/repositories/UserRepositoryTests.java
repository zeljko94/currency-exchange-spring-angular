package hr.dice.assignments.currency_exchange.repositories;

import hr.dice.assignments.currency_exchange.models.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTests {

    @Mock
    private UserRepository userRepository;

    @Test
    void testFindByUsernameAndPassword_WhenUserExists() {
        // Arrange
        String username = "testUser";
        String password = "testPassword";
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUsername(username);
        userEntity.setPassword(password);

        when(userRepository.findByUsernameAndPassword(username, password))
                .thenReturn(Optional.of(userEntity));

        // Act
        Optional<UserEntity> result = userRepository.findByUsernameAndPassword(username, password);

        // Assert
        assertEquals(userEntity, result.orElse(null));
        verify(userRepository, times(1)).findByUsernameAndPassword(username, password);
    }

    @Test
    void testFindByUsernameAndPassword_WhenUserDoesNotExist() {
        // Arrange
        String username = "testUser";
        String password = "testPassword";

        when(userRepository.findByUsernameAndPassword(username, password))
                .thenReturn(Optional.empty());

        // Act
        Optional<UserEntity> result = userRepository.findByUsernameAndPassword(username, password);

        // Assert
        assertEquals(Optional.empty(), result);
        verify(userRepository, times(1)).findByUsernameAndPassword(username, password);
    }
}
