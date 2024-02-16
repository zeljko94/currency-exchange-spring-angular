package hr.dice.assignments.currency_exchange.controllers.interfaces;

import hr.dice.assignments.currency_exchange.dto.BalanceDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Interface for managing user balances.
 */
public interface BalanceController {

    /**
     * Retrieves the balance for a specific user.
     *
     * @param id The ID of the user.
     * @return ResponseEntity with the BalanceDto containing the user's balance.
     */
    ResponseEntity<BalanceDto> getBalance(@PathVariable Long id);

    /**
     * Inserts a new balance for a user.
     *
     * @param newBalanceDto The BalanceDto containing the new balance information.
     * @param userId        The ID of the user.
     * @return ResponseEntity with the BalanceDto of the newly inserted balance.
     */
    ResponseEntity<BalanceDto> insertBalance(@RequestBody BalanceDto newBalanceDto, @PathVariable Long userId);

    /**
     * Adds money to the user's balance.
     *
     * @param id     The ID of the user's balance.
     * @param amount The amount to be added.
     * @return ResponseEntity with the updated BalanceDto.
     */
    ResponseEntity<BalanceDto> addMoneyToBalance(@PathVariable Long id, @PathVariable Double amount);

    /**
     * Subtracts money from the user's balance.
     *
     * @param id     The ID of the user's balance.
     * @param amount The amount to be subtracted.
     * @return ResponseEntity with the updated BalanceDto.
     */
    ResponseEntity<BalanceDto> subtractMoneyFromBalance(@PathVariable Long id, @PathVariable Double amount);

    /**
     * Transfers money from one balance to another.
     *
     * @param balanceFromId The ID of the balance to transfer money from.
     * @param balanceToId   The ID of the balance to transfer money to.
     * @param amount        The amount to transfer.
     * @return ResponseEntity with the updated BalanceDto.
     */
    ResponseEntity<BalanceDto> transferMoneyFromBalance(@PathVariable Long balanceFromId, @PathVariable Long balanceToId, @PathVariable Double amount);

    /**
     * Retrieves all balances associated with a user.
     *
     * @param userId The ID of the user.
     * @return ResponseEntity with a list of BalanceDto objects.
     */
    ResponseEntity<List<BalanceDto>> getBalancesForUser(@PathVariable Long userId);
}
