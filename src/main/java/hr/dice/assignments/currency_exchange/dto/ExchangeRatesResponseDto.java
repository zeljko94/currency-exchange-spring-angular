package hr.dice.assignments.currency_exchange.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Data transfer object (DTO) representing a response containing exchange rates.
 */
@Data
@Getter
@Setter
public class ExchangeRatesResponseDto {

    private Long lastUpdate;
    private String base;
    private Map<String, String> rates;

    /**
     * Default constructor for ExchangeRatesResponseDto.
     */
    public ExchangeRatesResponseDto() {
    }

    /**
     * Constructs an ExchangeRatesResponseDto with the specified last update time, base currency, and exchange rates.
     *
     * @param lastUpdate The timestamp of the last update.
     * @param base       The base currency code.
     * @param rates      A map containing exchange rates (target currency code -> exchange rate).
     */
    public ExchangeRatesResponseDto(Long lastUpdate, String base, Map<String, String> rates) {
        this.lastUpdate = lastUpdate;
        this.base = base;
        this.rates = rates;
    }

    /**
     * Retrieves the timestamp of the last update.
     *
     * @return The timestamp of the last update.
     */
    public Long getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets the timestamp of the last update.
     *
     * @param lastUpdate The timestamp of the last update.
     */
    public void setLastUpdate(Long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Retrieves the base currency code.
     *
     * @return The base currency code.
     */
    public String getBase() {
        return base;
    }

    /**
     * Sets the base currency code.
     *
     * @param base The base currency code.
     */
    public void setBase(String base) {
        this.base = base;
    }

    /**
     * Retrieves the map of exchange rates.
     *
     * @return A map containing exchange rates (target currency code -> exchange rate).
     */
    public Map<String, String> getRates() {
        return rates;
    }

    /**
     * Sets the map of exchange rates.
     *
     * @param rates A map containing exchange rates (target currency code -> exchange rate).
     */
    public void setRates(Map<String, String> rates) {
        this.rates = rates;
    }
}
