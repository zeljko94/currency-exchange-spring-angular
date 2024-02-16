package hr.dice.assignments.currency_exchange.repositories;

import hr.dice.assignments.currency_exchange.models.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for accessing and managing currency entities in the database.
 */
@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Long> {

    /**
     * Retrieves a currency entity by its code.
     *
     * @param code The code of the currency.
     * @return Optional containing the currency entity if found, empty otherwise.
     */
    Optional<CurrencyEntity> findByCode(String code);
}
