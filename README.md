# Currency Exchange App


Create a backend application that will store Bank balance information where the base currency is EUR. 
The Application should use this Free API https://anyapi.io/currency-exchange-api to get the current exchange 
rates and show the balance in a couple of other currencies (at least 3) each time a request is made to 
check the balance.

## Stack 

- Spring Boot 3
- PostgreSQL
  - Should be used as a docker container and connected to the app via `application.yaml` (Spring Auto Configuration)
- Hibernate (dependency `spring-boot-starter-jpa`)
- Junit5, Mockito (dependency `spring-boot-starter-test`)
- TestContainers

## Requirements

### 1. The App should expose REST endpoints

  - GET endpoint to fetch the current balance
  - PUT endpoint to add money to balance
  - PUT endpoint to subtract money from balance
    - We want to be able to control if a user should be allowed to "pay" something even if he does not have enough money in his account.
    With one property in `application.yaml` we should be able to control that use-case (accept or reject the payment depending 
    on the property set). If this control is disabled, then we should throw an exception in case the user does not have enough money.

All endpoints should validate data with Java Bean Validations where needed.

Response should look like this:
```json
{
  "balance": "123.45",
  "unit": "EUR",
  "exchange": {
    "GBP": "111.11",
    "USD": "222.22",
    "JPY": "333.33",
    ...
  }
}
```
>the values in other currencies are not correct, they are just provided as an example.

### 2. The App should implement a scheduler

The scheduler will run once a day and fetch the current day exchange rates and store them into the DB. Data in the DB should
not be overridden, but each day a new record will be stored.
You can use `https://anyapi.io/currency-exchange-api` or any other free API that you find useful. If the API requires an API key, 
that key should be provided inside the `application.yaml` file.

This part will be used to be able to calculate the current balance in other currencies when executing the GET endpoint to fetch 
the current balance.
>use the latest currency exchange rates to calculate the balance value in other currencies.

### 3. Test the application

- Each API endpoint should be tested with all positive and negative cases.
- The scheduler should be tested when the API endpoint returns some result (200 OK HTTP response) and when it responds 
  with 500 Internal Server Error HTTP response.
  - you can use Mockito to mock the API client behaviour.
- For using the DB in tests, use [TestContainers](https://www.testcontainers.org/)

### BONUS POINTS:

- Use [MapStruct](https://mapstruct.org/) to map Entity to DTO models.
- Use Controller Advice to handle any exception and create a ErrorDTO model to have a consistent exception model which will
  contain following properties:
    - timestamp: UTC timestamp
    - message: additional message about the error
    - path: request path
    - errors: key/value pair to show validation errors where the key is the field/property in question and the value is 
    the validation error message
- Generate OpenAPI documentation from the created REST Controller/s.
- Use bean property configuration with `@ConfigurationProperties` annotation instead of property field injection with the `@Value` annotation.
- Each created bean (class annotated with `@Component`, `@Service`, `@Controller` or `@RestController`) should have a seperated Interface
  definition which is documented with JavaDoc and a separate implementation which contains the actual logic.
- Instead of writing the Rest Client your self, use OpenApi definitions or interface definitions to generate a client implementation
  with any generator you find useful. Example would be a FeignClient with `OpenFeign` from the `Spring Cloud` project.
- Create and Update DB schemas with [Flyway](https://flywaydb.org/) Migration scripts.
- Use [Flyway Test Extensions](https://github.com/flyway/flyway-test-extensions) to populate DB data when running tests

## USEFUL LINKS

- [IOC & Dependency Injection](https://www.baeldung.com/inversion-control-and-dependency-injection-in-spring)
- [Spring Auto Configuration](https://docs.spring.io/spring-boot/docs/2.0.x/reference/html/using-boot-auto-configuration.html#:~:text=Spring%20Boot%20auto-configuration%20attempts,configures%20an%20in-memory%20database)
- [Flyway](https://www.baeldung.com/database-migrations-with-flyway)
- [Spring Boot Hibernate](https://www.baeldung.com/spring-boot-hibernate)
- [Java Bean Validations](https://www.baeldung.com/javax-validation)