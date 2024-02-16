package hr.dice.assignments.currency_exchange.scheduler.interfaces;

/**
 * Interface for managing scheduled tasks related to currency exchange rates.
 */
public interface CurrencyExchangeScheduler {

    /**
     * Scheduled task to fetch and store exchange rates.
     * It runs every day at midnight.
     */
    void fetchAndStoreExchangeRates();
}
