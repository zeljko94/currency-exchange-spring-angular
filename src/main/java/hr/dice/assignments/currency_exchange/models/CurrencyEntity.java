package hr.dice.assignments.currency_exchange.models;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class representing a currency.
 */
@Entity
@Table(name = "currencies")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class CurrencyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;

    /**
     * Gets the unique identifier of the currency.
     *
     * @return the currency ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the currency.
     *
     * @param id the currency ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the code of the currency.
     *
     * @return the currency code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the code of the currency.
     *
     * @param code the currency code.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the name of the currency.
     *
     * @return the currency name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the currency.
     *
     * @param name the currency name.
     */
    public void setName(String name) {
        this.name = name;
    }
}
