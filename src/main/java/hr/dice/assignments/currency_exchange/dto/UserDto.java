package hr.dice.assignments.currency_exchange.dto;

/**
 * Data transfer object (DTO) representing a user.
 */
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String role;

    /**
     * Constructs a UserDto with the specified parameters.
     *
     * @param id       The user's ID.
     * @param username The user's username.
     * @param password The user's password.
     * @param role     The user's role.
     */
    public UserDto(Long id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * Retrieves the user's ID.
     *
     * @return The user's ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the user's ID.
     *
     * @param id The user's ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the user's username.
     *
     * @return The user's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username.
     *
     * @param username The user's username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the user's password.
     *
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     *
     * @param password The user's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the user's role.
     *
     * @return The user's role.
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the user's role.
     *
     * @param role The user's role.
     */
    public void setRole(String role) {
        this.role = role;
    }
}
