package hr.dice.assignments.currency_exchange.models;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class representing a financial balance.
 */
@Entity
@Table(name = "balances")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class BalanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private CurrencyEntity currency;

    private Double amount;

    /**
     * Gets the unique identifier of the balance.
     *
     * @return the balance ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the balance.
     *
     * @param id the balance ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the user associated with the balance.
     *
     * @return the user entity.
     */
    public UserEntity getUser() {
        return user;
    }

    /**
     * Sets the user associated with the balance.
     *
     * @param user the user entity.
     */
    public void setUser(UserEntity user) {
        this.user = user;
    }

    /**
     * Gets the currency associated with the balance.
     *
     * @return the currency entity.
     */
    public CurrencyEntity getCurrency() {
        return currency;
    }

    /**
     * Sets the currency associated with the balance.
     *
     * @param currency the currency entity.
     */
    public void setCurrency(CurrencyEntity currency) {
        this.currency = currency;
    }

    /**
     * Gets the amount of the balance.
     *
     * @return the balance amount.
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Sets the amount of the balance.
     *
     * @param amount the balance amount.
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * Gets the name of the balance.
     *
     * @return the balance name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the balance.
     *
     * @param name the balance name.
     */
    public void setName(String name) {
        this.name = name;
    }
}
