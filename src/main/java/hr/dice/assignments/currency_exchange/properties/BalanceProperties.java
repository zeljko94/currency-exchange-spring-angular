package hr.dice.assignments.currency_exchange.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration properties for balance-related settings.
 */
@Component
@ConfigurationProperties(prefix = "balance")
public class BalanceProperties {

    /**
     * Flag indicating whether subtraction operations are allowed without sufficient funds.
     */
    private boolean allowSubtractWithoutFunds;

    /**
     * Getter method for allowSubtractWithoutFunds property.
     * @return true if subtraction without funds is allowed, false otherwise.
     */
    public boolean isAllowSubtractWithoutFunds() {
        return allowSubtractWithoutFunds;
    }

    /**
     * Setter method for allowSubtractWithoutFunds property.
     * @param allowSubtractWithoutFunds true to allow subtraction without funds, false otherwise.
     */
    public void setAllowSubtractWithoutFunds(boolean allowSubtractWithoutFunds) {
        this.allowSubtractWithoutFunds = allowSubtractWithoutFunds;
    }
}
