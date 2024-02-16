package hr.dice.assignments.currency_exchange.repositories;

import hr.dice.assignments.currency_exchange.models.CurrencyEntity;
import hr.dice.assignments.currency_exchange.models.ExchangeRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for accessing and managing exchange rate entities in the database.
 */
public interface ExchangeRateRepository extends JpaRepository<ExchangeRateEntity, Long> {

    /**
     * Retrieves an optional exchange rate entity based on the source and target currencies.
     *
     * @param baseCurrency   The source currency for the exchange rate.
     * @param targetCurrency The target currency for the exchange rate.
     * @return Optional containing the exchange rate entity if found, empty otherwise.
     */
    Optional<ExchangeRateEntity> findBySourceCurrencyAndTargetCurrency(CurrencyEntity baseCurrency, CurrencyEntity targetCurrency);
}
