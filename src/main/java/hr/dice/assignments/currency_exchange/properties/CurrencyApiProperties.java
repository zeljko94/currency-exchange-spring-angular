package hr.dice.assignments.currency_exchange.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration properties for Currency API.
 */
@Component
@ConfigurationProperties(prefix = "currency.api")
public class CurrencyApiProperties {

    private String url;
    private String convertAmountEndpoint;
    private String exchangeRatesEndpoint;
    private String key;

    /**
     * Get the base URL of the Currency API.
     *
     * @return The URL string.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set the base URL of the Currency API.
     *
     * @param url The URL string.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Get the endpoint for converting currency amounts.
     *
     * @return The endpoint string.
     */
    public String getConvertAmountEndpoint() {
        return convertAmountEndpoint;
    }

    /**
     * Set the endpoint for converting currency amounts.
     *
     * @param convertAmountEndpoint The endpoint string.
     */
    public void setConvertAmountEndpoint(String convertAmountEndpoint) {
        this.convertAmountEndpoint = convertAmountEndpoint;
    }

    /**
     * Get the endpoint for retrieving exchange rates.
     *
     * @return The endpoint string.
     */
    public String getExchangeRatesEndpoint() {
        return exchangeRatesEndpoint;
    }

    /**
     * Set the endpoint for retrieving exchange rates.
     *
     * @param exchangeRatesEndpoint The endpoint string.
     */
    public void setExchangeRatesEndpoint(String exchangeRatesEndpoint) {
        this.exchangeRatesEndpoint = exchangeRatesEndpoint;
    }

    /**
     * Get the API key required for accessing the Currency API.
     *
     * @return The API key string.
     */
    public String getKey() {
        return key;
    }

    /**
     * Set the API key required for accessing the Currency API.
     *
     * @param key The API key string.
     */
    public void setKey(String key) {
        this.key = key;
    }
}
