package hr.dice.assignments.currency_exchange.services.interfaces;

import hr.dice.assignments.currency_exchange.dto.ConvertAmountResponseDto;
import hr.dice.assignments.currency_exchange.dto.ExchangeRatesResponseDto;

/**
 * Interface defining methods for currency exchange operations.
 */
public interface CurrencyExchangeService {

    /**
     * Retrieves the latest exchange rates for a given base currency.
     *
     * @param base The currency code for the base currency.
     * @return ExchangeRatesResponseDto containing the latest exchange rates.
     */
    ExchangeRatesResponseDto getExchangeRates(String base);

    /**
     * Converts a specified amount from one currency to another.
     *
     * @param base   The currency code of the source currency.
     * @param to     The currency code of the target currency.
     * @param amount The amount to be converted.
     * @return ConvertAmountResponseDto containing the converted amount and exchange rate.
     */
    ConvertAmountResponseDto convertAmount(String base, String to, double amount);

    /**
     * Converts a specified amount from one currency to another.
     *
     * @param fromCurrencyCode The currency code of the source currency.
     * @param toCurrencyCode   The currency code of the target currency.
     * @param amount           The amount to be converted.
     * @return The converted amount.
     */
    Double convertCurrency(String fromCurrencyCode, String toCurrencyCode, Double amount);

    /**
     * Fetches the latest exchange rates from the API and saves them to the database.
     *
     * @param base The currency code for the base currency.
     */
    void fetchAndSaveExchangeRates(String base);
}
