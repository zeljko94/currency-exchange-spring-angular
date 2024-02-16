package hr.dice.assignments.currency_exchange.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

/**
 * Entity class representing an exchange rate between two currencies.
 */
@Entity
@Table(name = "exchange_rates")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ExchangeRateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_currency_id")
    private CurrencyEntity sourceCurrency;

    @ManyToOne
    @JoinColumn(name = "target_currency_id")
    private CurrencyEntity targetCurrency;

    private BigDecimal rate;

    @Column(name = "inserted_at")
    private LocalDateTime insertedAt;

    /**
     * Gets the unique identifier of the exchange rate.
     *
     * @return the exchange rate ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the exchange rate.
     *
     * @param id the exchange rate ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the source currency of the exchange rate.
     *
     * @return the source currency.
     */
    public CurrencyEntity getSourceCurrency() {
        return sourceCurrency;
    }

    /**
     * Sets the source currency of the exchange rate.
     *
     * @param sourceCurrency the source currency.
     */
    public void setSourceCurrency(CurrencyEntity sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    /**
     * Gets the target currency of the exchange rate.
     *
     * @return the target currency.
     */
    public CurrencyEntity getTargetCurrency() {
        return targetCurrency;
    }

    /**
     * Sets the target currency of the exchange rate.
     *
     * @param targetCurrency the target currency.
     */
    public void setTargetCurrency(CurrencyEntity targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    /**
     * Gets the exchange rate value.
     *
     * @return the exchange rate value.
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * Sets the exchange rate value.
     *
     * @param rate the exchange rate value.
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    /**
     * Gets the timestamp when the exchange rate was inserted.
     *
     * @return the insertion timestamp.
     */
    public LocalDateTime getInsertedAt() {
        return insertedAt;
    }

    /**
     * Sets the timestamp when the exchange rate was inserted.
     *
     * @param insertedAt the insertion timestamp.
     */
    public void setInsertedAt(LocalDateTime insertedAt) {
        this.insertedAt = insertedAt;
    }
}
