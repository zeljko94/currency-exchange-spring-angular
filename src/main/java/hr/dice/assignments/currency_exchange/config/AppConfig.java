package hr.dice.assignments.currency_exchange.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration class for defining application beans.
 */
@Configuration
public class AppConfig {

    /**
     * Creates and configures a RestTemplate bean, which is a Spring class for making HTTP requests.
     *
     * @return RestTemplate bean configured for making HTTP requests.
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
