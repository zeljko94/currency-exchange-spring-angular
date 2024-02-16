package hr.dice.assignments.currency_exchange.config.interfaces;

import hr.dice.assignments.currency_exchange.dto.ConvertAmountResponseDto;
import hr.dice.assignments.currency_exchange.dto.ExchangeRatesResponseDto;

/**
 * Interface for interacting with a third-party currency exchange API.
 */
public interface CurrencyExchangeApiClient {

    /**
     * Retrieves exchange rates for a specified base currency from the API.
     *
     * @param base The base currency code.
     * @return ExchangeRatesResponseDto containing exchange rates.
     */
    ExchangeRatesResponseDto getExchangeRates(String base);

    /**
     * Converts an amount from one currency to another using the API.
     *
     * @param base   The base currency code.
     * @param to     The target currency code.
     * @param amount The amount to convert.
     * @return ConvertAmountResponseDto containing the converted amount.
     */
    ConvertAmountResponseDto convertAmount(String base, String to, double amount);
}
