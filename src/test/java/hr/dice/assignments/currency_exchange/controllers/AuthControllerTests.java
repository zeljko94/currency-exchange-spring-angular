package hr.dice.assignments.currency_exchange.controllers;

import hr.dice.assignments.currency_exchange.dto.LoginDto;
import hr.dice.assignments.currency_exchange.dto.UserDto;
import hr.dice.assignments.currency_exchange.services.interfaces.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AuthControllerTests {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLogin_Successful() {
        // Mocking the login method of AuthService
        when(authService.login(anyString(), anyString())).thenReturn(new UserDto(1L, "username", "email", "role"));

        // Creating a LoginDto
        LoginDto loginDto = new LoginDto("username", "password");

        // Calling the login method of AuthController
        ResponseEntity<UserDto> response = authController.login(loginDto);

        // Verifying that the AuthService.login method was called with the correct arguments
        verify(authService, times(1)).login("username", "password");

        // Verifying that the response status is OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verifying that the response body contains the expected UserDto
        assertEquals(1L, response.getBody().getId());
        assertEquals("username", response.getBody().getUsername());
    }

    @Test
    public void testLogin_Unsuccessful() {
        // Mocking the login method of AuthService to return null
        when(authService.login(anyString(), anyString())).thenReturn(null);

        // Creating a LoginDto
        LoginDto loginDto = new LoginDto("username", "password");

        // Calling the login method of AuthController
        ResponseEntity<UserDto> response = authController.login(loginDto);

        // Verifying that the AuthService.login method was called with the correct arguments
        verify(authService, times(1)).login("username", "password");

        // Verifying that the response status is NOT_FOUND
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
