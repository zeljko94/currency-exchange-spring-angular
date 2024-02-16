package hr.dice.assignments.currency_exchange.repositories;

import hr.dice.assignments.currency_exchange.models.BalanceEntity;
import hr.dice.assignments.currency_exchange.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for accessing and managing balance entities in the database.
 */
@Repository
public interface BalanceRepository extends JpaRepository<BalanceEntity, Long> {

    /**
     * Retrieves a list of balances associated with a specific user.
     *
     * @param user The user entity for which balances are to be retrieved.
     * @return List of balance entities associated with the given user.
     */
    List<BalanceEntity> findByUser(UserEntity user);
}
