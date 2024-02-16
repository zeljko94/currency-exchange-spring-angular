package hr.dice.assignments.currency_exchange.controllers;

import hr.dice.assignments.currency_exchange.dto.CurrencyDto;
import hr.dice.assignments.currency_exchange.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller class for handling currency-related requests.
 */
@RestController
@RequestMapping("/api/currency")
public class CurrencyController implements hr.dice.assignments.currency_exchange.controllers.interfaces.CurrencyController {

    private final CurrencyService currencyService;

    /**
     * Constructor for CurrencyController.
     *
     * @param currencyService CurrencyService implementation for handling currency-related logic.
     */
    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    /**
     * Handles the retrieval of all currencies.
     *
     * @return ResponseEntity with a list of CurrencyDto if successful, HttpStatus.OK otherwise.
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<CurrencyDto>> getAll() {
        return ResponseEntity.ok(currencyService.getAll());
    }

    /**
     * Handles the retrieval of a currency by its ID.
     *
     * @param id Currency ID for which the currency should be retrieved.
     * @return ResponseEntity with the CurrencyDto if successful, HttpStatus.OK otherwise.
     */
    @GetMapping("/getById/{id}")
    public ResponseEntity<CurrencyDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(currencyService.getById(id));
    }

    /**
     * Handles the retrieval of a currency by its code.
     *
     * @param code Currency code for which the currency should be retrieved.
     * @return ResponseEntity with the CurrencyDto if successful, HttpStatus.OK otherwise.
     */
    @GetMapping("/getByCode/{code}")
    public ResponseEntity<CurrencyDto> getByCode(@PathVariable String code) {
        return ResponseEntity.ok(currencyService.getByCode(code));
    }
}
