package hr.dice.assignments.currency_exchange.services;

import hr.dice.assignments.currency_exchange.dto.UserDto;
import hr.dice.assignments.currency_exchange.models.UserEntity;
import hr.dice.assignments.currency_exchange.repositories.UserRepository;
import hr.dice.assignments.currency_exchange.util.EntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AuthServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private EntityMapper entityMapper;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testLogin_Success() {
        // Arrange
        String username = "testUser";
        String password = "testPassword";
        String role     = "user";
        Long userId = 1L;
        UserEntity userEntity = new UserEntity(userId, username, password, role);
        UserDto userDto = new UserDto(userId, username, password, role);

        when(userRepository.findByUsernameAndPassword(anyString(), anyString())).thenReturn(Optional.of(userEntity));
        when(entityMapper.entityToDTO(userEntity)).thenReturn(userDto);

        // Act
        UserDto result = authService.login(username, password);

        // Assert
        assertNotNull(result);
        assertEquals(userId, result.getId());
        assertEquals(username, result.getUsername());
        assertEquals(password, result.getPassword());
        verify(userRepository, times(1)).findByUsernameAndPassword(username, password);
        verify(entityMapper, times(1)).entityToDTO(userEntity);
    }

    @Test
    void testLogin_UserNotFound() {
        // Arrange
        String username = "nonExistingUser";
        String password = "nonExistingPassword";

        when(userRepository.findByUsernameAndPassword(anyString(), anyString())).thenReturn(Optional.empty());

        // Act
        UserDto result = authService.login(username, password);

        // Assert
        assertNull(result);
        verify(userRepository, times(1)).findByUsernameAndPassword(username, password);
        verify(entityMapper, never()).entityToDTO((UserEntity) any());
    }
}
