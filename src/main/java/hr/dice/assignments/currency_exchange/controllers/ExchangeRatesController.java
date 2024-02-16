package hr.dice.assignments.currency_exchange.controllers;

import hr.dice.assignments.currency_exchange.dto.ConvertAmountResponseDto;
import hr.dice.assignments.currency_exchange.dto.ExchangeRatesResponseDto;
import hr.dice.assignments.currency_exchange.services.CurrencyExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling exchange rates-related requests.
 */
@RestController
@RequestMapping("/api/exchange-rates")
public class ExchangeRatesController implements hr.dice.assignments.currency_exchange.controllers.interfaces.ExchangeRatesController {

    private final CurrencyExchangeService currencyExchangeService;

    /**
     * Constructor for ExchangeRatesController.
     *
     * @param currencyExchangeService CurrencyExchangeService implementation for handling exchange rates-related logic.
     */
    @Autowired
    public ExchangeRatesController(CurrencyExchangeService currencyExchangeService) {
        this.currencyExchangeService = currencyExchangeService;
    }

    /**
     * Handles the retrieval of exchange rates for a given base currency.
     *
     * @param base Base currency for which exchange rates should be retrieved.
     * @return ResponseEntity with the ExchangeRatesResponseDto if successful, HttpStatus.OK otherwise.
     */
    @GetMapping("/{base}")
    public ResponseEntity<ExchangeRatesResponseDto> getExchangeRates(@PathVariable String base) {
        ExchangeRatesResponseDto result = currencyExchangeService.getExchangeRates(base);
        return ResponseEntity.ok(result);
    }

    /**
     * Handles the conversion of an amount between two currencies.
     *
     * @param base   Base currency for the conversion.
     * @param to     Target currency for the conversion.
     * @param amount Amount to be converted.
     * @return ResponseEntity with the ConvertAmountResponseDto if successful, HttpStatus.OK otherwise.
     */
    @GetMapping("/convertAmount/{base}/{to}/{amount}")
    public ResponseEntity<ConvertAmountResponseDto> convertAmount(@PathVariable String base, @PathVariable String to, @PathVariable double amount) {
        ConvertAmountResponseDto result = currencyExchangeService.convertAmount(base, to, amount);
        return ResponseEntity.ok(result);
    }

    /**
     * Handles the testing of fetching and saving exchange rates for a given base currency.
     *
     * @return ResponseEntity with HttpStatus.OK if successful.
     */
    @GetMapping("/testFetchRates")
    public ResponseEntity<HttpStatus> testFetchRates() {
        currencyExchangeService.fetchAndSaveExchangeRates("EUR");
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
