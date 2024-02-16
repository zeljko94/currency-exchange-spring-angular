package hr.dice.assignments.currency_exchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication()
@EnableJpaRepositories("hr.dice.assignments.currency_exchange.*")
@ComponentScan(basePackages = { "hr.dice.assignments.currency_exchange.*" })
@EntityScan("hr.dice.assignments.currency_exchange.models")
@EnableScheduling
public class CurrencyExchangeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyExchangeApplication.class, args);
    }

}
