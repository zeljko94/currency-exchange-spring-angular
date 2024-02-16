package hr.dice.assignments.currency_exchange.controllers.interfaces;

import hr.dice.assignments.currency_exchange.dto.CurrencyDto;
import hr.dice.assignments.currency_exchange.models.CurrencyEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

/**
 * Interface for managing currencies.
 */
public interface CurrencyController {

    /**
     * Retrieves all currencies.
     *
     * @return ResponseEntity with a list of CurrencyDto objects.
     */
    ResponseEntity<List<CurrencyDto>> getAll();

    /**
     * Retrieves a currency by its ID.
     *
     * @param id The ID of the currency.
     * @return ResponseEntity with a CurrencyDto if found.
     */
    ResponseEntity<CurrencyDto> getById(@PathVariable Long id);

    /**
     * Retrieves a currency by its code.
     *
     * @param code The code of the currency.
     * @return ResponseEntity with CurrencyDto if found.
     */
    ResponseEntity<CurrencyDto> getByCode(@PathVariable String code);
}
