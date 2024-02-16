package hr.dice.assignments.currency_exchange.dto;

/**
 * Data transfer object (DTO) representing the response for converting an amount from one currency to another.
 */
public class ConvertAmountResponseDto {
    private String base;
    private String to;
    private double amount;
    private double converted;
    private double rate;
    private long lastUpdate;

    /**
     * Default constructor for ConvertAmountResponseDto.
     */
    public ConvertAmountResponseDto() {
    }

    /**
     * Parameterized constructor for ConvertAmountResponseDto.
     *
     * @param base       The base currency code.
     * @param to         The target currency code.
     * @param amount     The amount to convert.
     * @param converted  The converted amount.
     * @param rate       The exchange rate used for conversion.
     * @param lastUpdate The timestamp of the last update.
     */
    public ConvertAmountResponseDto(String base, String to, double amount, double converted, double rate, long lastUpdate) {
        this.base = base;
        this.to = to;
        this.amount = amount;
        this.converted = converted;
        this.rate = rate;
        this.lastUpdate = lastUpdate;
    }

    /**
     * Retrieves the base currency code.
     *
     * @return The base currency code.
     */
    public String getBase() {
        return base;
    }

    /**
     * Sets the base currency code.
     *
     * @param base The base currency code.
     */
    public void setBase(String base) {
        this.base = base;
    }

    /**
     * Retrieves the target currency code.
     *
     * @return The target currency code.
     */
    public String getTo() {
        return to;
    }

    /**
     * Sets the target currency code.
     *
     * @param to The target currency code.
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * Retrieves the amount to convert.
     *
     * @return The amount to convert.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the amount to convert.
     *
     * @param amount The amount to convert.
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Retrieves the converted amount.
     *
     * @return The converted amount.
     */
    public double getConverted() {
        return converted;
    }

    /**
     * Sets the converted amount.
     *
     * @param converted The converted amount.
     */
    public void setConverted(double converted) {
        this.converted = converted;
    }

    /**
     * Retrieves the exchange rate used for conversion.
     *
     * @return The exchange rate used for conversion.
     */
    public double getRate() {
        return rate;
    }

    /**
     * Sets the exchange rate used for conversion.
     *
     * @param rate The exchange rate used for conversion.
     */
    public void setRate(double rate) {
        this.rate = rate;
    }

    /**
     * Retrieves the timestamp of the last update.
     *
     * @return The timestamp of the last update.
     */
    public long getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets the timestamp of the last update.
     *
     * @param lastUpdate The timestamp of the last update.
     */
    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
