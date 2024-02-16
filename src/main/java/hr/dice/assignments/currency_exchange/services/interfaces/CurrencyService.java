package hr.dice.assignments.currency_exchange.services.interfaces;

import hr.dice.assignments.currency_exchange.dto.CurrencyDto;

import java.util.List;

/**
 * Interface defining methods for managing currency entities.
 */
public interface CurrencyService {

    /**
     * Retrieves all CurrencyDto instances.
     *
     * @return List of CurrencyDto instances.
     */
    List<CurrencyDto> getAll();

    /**
     * Retrieves a CurrencyDto instance by its ID.
     *
     * @param id ID of the CurrencyDto.
     * @return CurrencyDto if found, otherwise null.
     */
    CurrencyDto getById(Long id);

    /**
     * Retrieves a CurrencyDto instance by its code.
     *
     * @param code Code of the CurrencyDto.
     * @return CurrencyDto if found, otherwise null.
     */
    CurrencyDto getByCode(String code);
}
