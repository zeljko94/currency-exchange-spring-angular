package hr.dice.assignments.currency_exchange.services;

import hr.dice.assignments.currency_exchange.dto.UserDto;
import hr.dice.assignments.currency_exchange.models.UserEntity;
import hr.dice.assignments.currency_exchange.repositories.UserRepository;
import hr.dice.assignments.currency_exchange.util.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for handling authentication-related operations.
 */
@Service
public class AuthService implements hr.dice.assignments.currency_exchange.services.interfaces.AuthService {
    private final EntityMapper mapper;

    private final UserRepository userRepository;

    /**
     * Constructor for AuthService.
     *
     * @param userRepository UserRepository for accessing user data.
     */
    @Autowired
    public AuthService(EntityMapper mapper, UserRepository userRepository) {
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    /**
     * Attempts to authenticate a user with the provided username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return UserDto if successful, null otherwise.
     */
    @Override
    public UserDto login(String username, String password) {
        Optional<UserEntity> user = userRepository.findByUsernameAndPassword(username, password);

        if (user.isPresent()) {
            return mapper.entityToDTO(user.get());
        }
        return null;
    }
}
