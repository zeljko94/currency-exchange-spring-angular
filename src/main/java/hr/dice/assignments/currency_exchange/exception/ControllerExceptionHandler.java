package hr.dice.assignments.currency_exchange.exception;

import hr.dice.assignments.currency_exchange.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for controllers.
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

    /**
     * Handles validation exceptions and returns a ResponseEntity with a detailed error message.
     *
     * @param ex      MethodArgumentNotValidException instance representing the validation error.
     * @param request HttpServletRequest for the current request.
     * @return ResponseEntity with an ErrorDto containing information about the validation failure.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDto> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ErrorDto errorDTO = new ErrorDto(LocalDateTime.now(), "Validation Failed", request.getRequestURL().toString(), errors);
        return ResponseEntity.badRequest().body(errorDTO);
    }

    /**
     * Handles PaymentRequiredException and returns a ResponseEntity with a custom error message.
     *
     * @param ex      PaymentRequiredException instance representing the payment required error.
     * @param request HttpServletRequest for the current request.
     * @return ResponseEntity with an ErrorDto containing information about the payment required error.
     */
    @ExceptionHandler(CurrencyExchangeAPIKeyExpiredException.class)
    @ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
    public ResponseEntity<ErrorDto> handlePaymentRequiredException(CurrencyExchangeAPIKeyExpiredException ex, HttpServletRequest request) {
        ErrorDto errorDTO = new ErrorDto(LocalDateTime.now(), "Payment Required", request.getRequestURL().toString(), null);
        return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(errorDTO);
    }
}
