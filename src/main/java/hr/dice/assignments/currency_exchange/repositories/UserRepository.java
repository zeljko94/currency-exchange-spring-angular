package hr.dice.assignments.currency_exchange.repositories;

import hr.dice.assignments.currency_exchange.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for accessing and managing user entities in the database.
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Retrieves an optional user entity based on the username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return Optional containing the user entity if found, empty otherwise.
     */
    Optional<UserEntity> findByUsernameAndPassword(String username, String password);
}
