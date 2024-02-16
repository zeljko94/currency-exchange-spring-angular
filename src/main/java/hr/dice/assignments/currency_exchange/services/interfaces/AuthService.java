package hr.dice.assignments.currency_exchange.services.interfaces;

import hr.dice.assignments.currency_exchange.dto.UserDto;

/**
 * Interface defining authentication service methods.
 */
public interface AuthService {

    /**
     * Performs user login authentication.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return UserDto representing the authenticated user.
     */
    UserDto login(String username, String password);
}
