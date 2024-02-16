package hr.dice.assignments.currency_exchange.controllers.interfaces;

import hr.dice.assignments.currency_exchange.dto.LoginDto;
import hr.dice.assignments.currency_exchange.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Interface for handling authentication operations.
 */
public interface AuthController {
    /**
     * Handles the login request.
     *
     * @param loginDto LoginDto containing username and password for authentication.
     * @return ResponseEntity with the authenticated UserDto if successful,
     * HttpStatus.NOT_FOUND otherwise.
     */
    ResponseEntity<UserDto> login(@RequestBody LoginDto loginDto);
}
