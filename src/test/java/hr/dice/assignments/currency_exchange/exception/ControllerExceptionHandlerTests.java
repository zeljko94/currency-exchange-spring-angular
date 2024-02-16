package hr.dice.assignments.currency_exchange.exception;

import hr.dice.assignments.currency_exchange.dto.ErrorDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ControllerExceptionHandlerTests {

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private ControllerExceptionHandler controllerExceptionHandler;

    @Test
    void testHandleValidationException() {
        // Arrange
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        when(exception.getBindingResult().getAllErrors()).thenReturn(Collections.singletonList(new FieldError("object", "field", "error message")));
        when(request.getRequestURL()).thenReturn(new StringBuffer("http://localhost:8080/api"));
        Map<String, String> errors = new HashMap<>();
        errors.put("field", "error message");

        // Act
        ResponseEntity<ErrorDto> response = controllerExceptionHandler.handleValidationException(exception, request);

        // Assert
        ErrorDto expectedDto = new ErrorDto(LocalDateTime.now(), "Validation Failed", "http://localhost:8080/api", errors);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedDto, response.getBody());
        verify(exception, times(1)).getBindingResult();
        verify(exception, times(1)).getBindingResult().getAllErrors();
    }

    @Test
    void testHandlePaymentRequiredException() {
        // Arrange
        CurrencyExchangeAPIKeyExpiredException exception = new CurrencyExchangeAPIKeyExpiredException("API Key Expired");
        when(request.getRequestURL()).thenReturn(new StringBuffer("http://localhost:8080/api"));

        // Act
        ResponseEntity<ErrorDto> response = controllerExceptionHandler.handlePaymentRequiredException(exception, request);

        // Assert
        ErrorDto expectedDto = new ErrorDto(LocalDateTime.now(), "Payment Required", "http://localhost:8080/api", null);
        assertEquals(HttpStatus.PAYMENT_REQUIRED, response.getStatusCode());
        assertEquals(expectedDto, response.getBody());
    }
}
