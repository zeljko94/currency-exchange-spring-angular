package hr.dice.assignments.currency_exchange.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Custom exception indicating insufficient funds for a financial transaction.
 */
@ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
public class CurrencyExchangeAPIKeyExpiredException extends RuntimeException {

    /**
     * Constructs a new InsufficientFundsException with the specified detail message.
     *
     * @param message the detail message.
     */
    public CurrencyExchangeAPIKeyExpiredException(String message) {
        super(message);
    }
}

