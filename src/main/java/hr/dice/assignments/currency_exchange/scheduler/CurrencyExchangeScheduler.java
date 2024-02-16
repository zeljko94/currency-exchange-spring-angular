package hr.dice.assignments.currency_exchange.scheduler;

import hr.dice.assignments.currency_exchange.services.CurrencyExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Scheduler component for periodic tasks related to currency exchange rates.
 */
@Component
public class CurrencyExchangeScheduler implements hr.dice.assignments.currency_exchange.scheduler.interfaces.CurrencyExchangeScheduler {

    private final CurrencyExchangeService currencyExchangeService;

    /**
     * Constructor for CurrencyExchangeScheduler.
     *
     * @param currencyExchangeService CurrencyExchangeService implementation for handling currency exchange tasks.
     */
    @Autowired
    public CurrencyExchangeScheduler(CurrencyExchangeService currencyExchangeService) {
        this.currencyExchangeService = currencyExchangeService;
    }

    /**
     * Scheduled task to fetch and store exchange rates.
     * It runs every day at midnight.
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void fetchAndStoreExchangeRates() {
        currencyExchangeService.fetchAndSaveExchangeRates("EUR");
    }
}
