package hr.dice.assignments.currency_exchange.services;

import hr.dice.assignments.currency_exchange.config.CurrencyExchangeApiClient;
import hr.dice.assignments.currency_exchange.dto.ConvertAmountResponseDto;
import hr.dice.assignments.currency_exchange.dto.ExchangeRatesResponseDto;
import hr.dice.assignments.currency_exchange.models.CurrencyEntity;
import hr.dice.assignments.currency_exchange.models.ExchangeRateEntity;
import hr.dice.assignments.currency_exchange.repositories.CurrencyRepository;
import hr.dice.assignments.currency_exchange.repositories.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Service class for handling currency exchange operations.
 */
@Service
public class CurrencyExchangeService implements hr.dice.assignments.currency_exchange.services.interfaces.CurrencyExchangeService {
    private final CurrencyRepository currencyRepository;
    private final ExchangeRateRepository exchangeRateRepository;
    private final CurrencyExchangeApiClient currencyExchangeApiClient;


    /**
     * Constructor for CurrencyExchangeService.
     *
     * @param currencyExchangeApiClient CurrencyExchangeApiClient for interacting with the currency exchange API.
     * @param exchangeRateRepository    ExchangeRateRepository for accessing exchange rate data.
     * @param currencyRepository        CurrencyRepository for accessing currency data.
     */
    @Autowired
    public CurrencyExchangeService(CurrencyExchangeApiClient currencyExchangeApiClient, ExchangeRateRepository exchangeRateRepository, CurrencyRepository currencyRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
        this.currencyExchangeApiClient = currencyExchangeApiClient;
        this.currencyRepository = currencyRepository;
    }

    /**
     * Retrieves the latest exchange rates for a given base currency.
     *
     * @param base The currency code for the base currency.
     * @return ExchangeRatesResponseDto containing the latest exchange rates.
     */
    public ExchangeRatesResponseDto getExchangeRates(String base) {
        return currencyExchangeApiClient.getExchangeRates(base);
    }

    /**
     * Converts a specified amount from one currency to another.
     *
     * @param base   The currency code of the source currency.
     * @param to     The currency code of the target currency.
     * @param amount The amount to be converted.
     * @return ConvertAmountResponseDto containing the converted amount and exchange rate.
     */
    public ConvertAmountResponseDto convertAmount(String base, String to, double amount) {
        return currencyExchangeApiClient.convertAmount(base, to, amount);
    }

    /**
     * Converts a specified amount from one currency to another.
     *
     * @param fromCurrencyCode The currency code of the source currency.
     * @param toCurrencyCode   The currency code of the target currency.
     * @param amount           The amount to be converted.
     * @return The converted amount.
     */
    public Double convertCurrency(String fromCurrencyCode, String toCurrencyCode, Double amount) {
        if (fromCurrencyCode.equals(toCurrencyCode)) {
            return amount;
        } else {
            // Fetch the exchange rate from the database or external API
            ConvertAmountResponseDto convertAmountResponseDto = convertAmount(fromCurrencyCode, toCurrencyCode, 1d);

            return amount * convertAmountResponseDto.getRate();
        }
    }

    /**
     * Fetches the latest exchange rates from the API and saves them to the database.
     *
     * @param base The currency code for the base currency.
     */
    public void fetchAndSaveExchangeRates(String base) {
        ExchangeRatesResponseDto exchangeRates = currencyExchangeApiClient.getExchangeRates(base);

        String baseCurrencyCode = exchangeRates.getBase();
        Map<String, String> apiRates = exchangeRates.getRates();

        // Get the base currency from the database or create it if not present
        CurrencyEntity baseCurrency = currencyRepository.findByCode(baseCurrencyCode)
                .orElseGet(() -> {
                    CurrencyEntity newCurrency = new CurrencyEntity();
                    newCurrency.setCode(baseCurrencyCode);
                    newCurrency.setName(baseCurrencyCode);
                    return currencyRepository.save(newCurrency);
                });

        // Iterate through API rates and add new currencies and exchange rates to the database
        for (Map.Entry<String, String> entry : apiRates.entrySet()) {
            String targetCurrencyCode = entry.getKey();
            String exchangeRateValue = entry.getValue();

            // Get or create the target currency
            CurrencyEntity targetCurrency = currencyRepository.findByCode(targetCurrencyCode)
                    .orElseGet(() -> {
                        CurrencyEntity newCurrency = new CurrencyEntity();
                        newCurrency.setCode(targetCurrencyCode);
                        newCurrency.setName(targetCurrencyCode);
                        return currencyRepository.save(newCurrency);
                    });

            // Check if the exchange rate already exists
            ExchangeRateEntity existingExchangeRate = exchangeRateRepository
                    .findBySourceCurrencyAndTargetCurrency(baseCurrency, targetCurrency)
                    .orElse(null);

            if (existingExchangeRate == null) {
                // Create and save the new exchange rate
                ExchangeRateEntity newExchangeRate = new ExchangeRateEntity();
                newExchangeRate.setSourceCurrency(baseCurrency);
                newExchangeRate.setTargetCurrency(targetCurrency);
                newExchangeRate.setRate(new BigDecimal(exchangeRateValue));
                newExchangeRate.setInsertedAt(LocalDateTime.now());
                exchangeRateRepository.save(newExchangeRate);
            }
        }
    }

}
