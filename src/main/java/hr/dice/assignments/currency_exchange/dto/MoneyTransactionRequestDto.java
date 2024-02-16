package hr.dice.assignments.currency_exchange.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

/**
 * Data transfer object (DTO) representing a money transaction request.
 */
public class MoneyTransactionRequestDto {

    @NotNull
    @DecimalMin(value = "0.01", message = "Amount must be greater than or equal to 0.01")
    private Double amount;

    /**
     * Constructs a MoneyTransactionRequestDto with the specified amount.
     *
     * @param amount The amount of money involved in the transaction.
     */
    public MoneyTransactionRequestDto(Double amount) {
        this.amount = amount;
    }

    /**
     * Retrieves the amount of money involved in the transaction.
     *
     * @return The amount of money.
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Sets the amount of money involved in the transaction.
     *
     * @param amount The amount of money.
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
