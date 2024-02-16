package hr.dice.assignments.currency_exchange.controllers;

import hr.dice.assignments.currency_exchange.dto.LoginDto;
import hr.dice.assignments.currency_exchange.dto.UserDto;
import hr.dice.assignments.currency_exchange.models.UserEntity;
import hr.dice.assignments.currency_exchange.services.interfaces.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Controller class for handling authentication-related requests.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController implements hr.dice.assignments.currency_exchange.controllers.interfaces.AuthController {

    private final AuthService authService;

    /**
     * Constructor for AuthController.
     *
     * @param authService AuthService implementation for handling authentication logic.
     */
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Handles the login request.
     *
     * @param loginDto LoginDto containing username and password for authentication.
     * @return ResponseEntity with the authenticated UserDto if successful, HttpStatus.NOT_FOUND otherwise.
     */
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginDto loginDto) {
        UserDto user = authService.login(loginDto.getUsername(), loginDto.getPassword());
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
