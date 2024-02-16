package hr.dice.assignments.currency_exchange.dto;

/**
 * Data transfer object (DTO) representing balance information.
 */
public class BalanceDto {
    private Long id;
    private String name;
    private String currencyCode;
    private Long userId;
    private Double amount;

    /**
     * Default constructor for BalanceDto.
     */
    public BalanceDto() {
    }

    /**
     * Parameterized constructor for BalanceDto.
     *
     * @param id           The ID of the balance.
     * @param name         The name of the balance.
     * @param currencyCode The currency code of the balance.
     * @param userId       The ID of the user associated with the balance.
     * @param amount       The amount of the balance.
     */
    public BalanceDto(Long id, String name, String currencyCode, Long userId, Double amount) {
        this.id = id;
        this.name = name;
        this.currencyCode = currencyCode;
        this.userId = userId;
        this.amount = amount;
    }

    /**
     * Retrieves the ID of the balance.
     *
     * @return The ID of the balance.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the balance.
     *
     * @param id The ID of the balance.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the balance.
     *
     * @return The name of the balance.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the balance.
     *
     * @param name The name of the balance.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the currency code of the balance.
     *
     * @return The currency code of the balance.
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * Sets the currency code of the balance.
     *
     * @param currencyCode The currency code of the balance.
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    /**
     * Retrieves the ID of the user associated with the balance.
     *
     * @return The ID of the user associated with the balance.
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user associated with the balance.
     *
     * @param userId The ID of the user associated with the balance.
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Retrieves the amount of the balance.
     *
     * @return The amount of the balance.
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Sets the amount of the balance.
     *
     * @param amount The amount of the balance.
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
