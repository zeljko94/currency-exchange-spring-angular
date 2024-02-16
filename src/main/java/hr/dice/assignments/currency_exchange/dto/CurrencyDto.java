package hr.dice.assignments.currency_exchange.dto;

/**
 * Data transfer object (DTO) representing a currency.
 */
public class CurrencyDto {
    private Long id;
    private String name;
    private String code;

    /**
     * Constructs a new CurrencyDto with the specified ID, name, and code.
     *
     * @param id   The ID of the currency.
     * @param name The name of the currency.
     * @param code The code of the currency.
     */
    public CurrencyDto(Long id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    /**
     * Retrieves the ID of the currency.
     *
     * @return The ID of the currency.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the currency.
     *
     * @param id The ID of the currency.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the currency.
     *
     * @return The name of the currency.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the currency.
     *
     * @param name The name of the currency.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the code of the currency.
     *
     * @return The code of the currency.
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the code of the currency.
     *
     * @param code The code of the currency.
     */
    public void setCode(String code) {
        this.code = code;
    }
}
