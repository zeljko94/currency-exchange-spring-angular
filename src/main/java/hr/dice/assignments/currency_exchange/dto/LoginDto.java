package hr.dice.assignments.currency_exchange.dto;

/**
 * Data transfer object (DTO) representing user login credentials.
 */
public class LoginDto {
    private String username;
    private String password;

    /**
     * Constructs a LoginDto with the specified username and password.
     *
     * @param username The username provided during login.
     * @param password The password provided during login.
     */
    public LoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Retrieves the username.
     *
     * @return The username provided during login.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username The username provided during login.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the password.
     *
     * @return The password provided during login.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password The password provided during login.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
