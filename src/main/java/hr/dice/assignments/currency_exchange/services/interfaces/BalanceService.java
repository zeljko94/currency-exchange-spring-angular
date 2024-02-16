package hr.dice.assignments.currency_exchange.services.interfaces;

import hr.dice.assignments.currency_exchange.dto.BalanceDto;

import java.util.List;

/**
 * Service interface for balance-related operations.
 */
public interface BalanceService {

    /**
     * Retrieves the balance information by its unique identifier.
     *
     * @param id The unique identifier of the balance.
     * @return BalanceDto representing the balance information.
     */
    BalanceDto getBalanceById(Long id);

    /**
     * Adds money to the balance identified by its unique identifier.
     *
     * @param id           The unique identifier of the balance.
     * @param amountToAdd  The amount of money to add.
     * @return BalanceDto representing the updated balance information.
     */
    BalanceDto addMoney(Long id, Double amountToAdd);

    /**
     * Subtracts money from the balance identified by its unique identifier.
     *
     * @param id               The unique identifier of the balance.
     * @param amountToSubtract The amount of money to subtract.
     * @return BalanceDto representing the updated balance information.
     */
    BalanceDto subtractMoney(Long id, Double amountToSubtract);

    /**
     * Transfers money from one balance to another.
     *
     * @param balanceFromId The unique identifier of the source balance.
     * @param balanceToId   The unique identifier of the destination balance.
     * @param amount        The amount of money to transfer.
     * @return BalanceDto representing the updated destination balance information.
     */
    BalanceDto transferMoney(Long balanceFromId, Long balanceToId, Double amount);

    /**
     * Retrieves balances associated with a specific user.
     *
     * @param userId The unique identifier of the user.
     * @return List of BalanceDto representing balances associated with the user.
     */
    List<BalanceDto> getBalancesForUser(Long userId);

    /**
     * Inserts a new balance for the specified user.
     *
     * @param newBalanceDto BalanceDto containing information for the new balance.
     * @param userId        The unique identifier of the user.
     * @return BalanceDto representing the newly inserted balance information.
     */
    BalanceDto insert(BalanceDto newBalanceDto, Long userId);
}
