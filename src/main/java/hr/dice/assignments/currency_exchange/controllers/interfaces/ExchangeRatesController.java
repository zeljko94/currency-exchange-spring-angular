package hr.dice.assignments.currency_exchange.controllers.interfaces;

import hr.dice.assignments.currency_exchange.dto.ConvertAmountResponseDto;
import hr.dice.assignments.currency_exchange.dto.ExchangeRatesResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Interface defining endpoints for handling exchange rates operations.
 */
public interface ExchangeRatesController {

    /**
     * Retrieves the exchange rates for a given base currency.
     *
     * @param base The base currency code.
     * @return ResponseEntity containing ExchangeRatesResponseDto with the exchange rates.
     */
    ResponseEntity<ExchangeRatesResponseDto> getExchangeRates(@PathVariable String base);

    /**
     * Converts an amount from one currency to another.
     *
     * @param base   The base currency code.
     * @param to     The target currency code.
     * @param amount The amount to convert.
     * @return ResponseEntity containing ConvertAmountResponseDto with the converted amount.
     */
    ResponseEntity<ConvertAmountResponseDto> convertAmount(@PathVariable String base, @PathVariable String to, @PathVariable double amount);

    /**
     * Tests the fetch rates functionality.
     *
     * @return ResponseEntity with HttpStatus indicating the test result.
     */
    ResponseEntity<HttpStatus> testFetchRates();
}
