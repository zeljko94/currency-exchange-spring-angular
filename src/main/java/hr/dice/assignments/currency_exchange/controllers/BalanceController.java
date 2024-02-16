package hr.dice.assignments.currency_exchange.controllers;

import hr.dice.assignments.currency_exchange.dto.BalanceDto;
import hr.dice.assignments.currency_exchange.services.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling balance-related requests.
 */
@RestController
@RequestMapping("/api/balance")
public class BalanceController implements hr.dice.assignments.currency_exchange.controllers.interfaces.BalanceController {

    private final BalanceService balanceService;

    /**
     * Constructor for BalanceController.
     *
     * @param balanceService BalanceService implementation for handling balance-related logic.
     */
    @Autowired
    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    /**
     * Handles the retrieval of a balance by its ID.
     *
     * @param id The ID of the balance.
     * @return ResponseEntity with the BalanceDto if successful, HttpStatus.OK otherwise.
     */
    @GetMapping("/{id}")
    public ResponseEntity<BalanceDto> getBalance(@PathVariable Long id) {
        BalanceDto balanceDTO = balanceService.getBalanceById(id);
        return ResponseEntity.ok(balanceDTO);
    }

    /**
     * Handles the insertion of a new balance for a user.
     *
     * @param newBalanceDto BalanceDto containing information for creating a new balance.
     * @param userId        User ID for whom the new balance should be created.
     * @return ResponseEntity with the created BalanceDto if successful, HttpStatus.OK otherwise.
     */
    @PostMapping("/insert/{userId}")
    public ResponseEntity<BalanceDto> insertBalance(@RequestBody BalanceDto newBalanceDto, @PathVariable Long userId) {
        return ResponseEntity.ok(balanceService.insert(newBalanceDto, userId));
    }

    /**
     * Handles the addition of money to a balance.
     *
     * @param id     The ID of the balance.
     * @param amount The amount of money to add.
     * @return ResponseEntity with the updated BalanceDto if successful, HttpStatus.OK otherwise.
     */
    @PutMapping("/add/{id}/{amount}")
    public ResponseEntity<BalanceDto> addMoneyToBalance(@PathVariable Long id, @PathVariable Double amount) {
        BalanceDto updatedBalance = balanceService.addMoney(id, amount);
        return ResponseEntity.ok(updatedBalance);
    }

    /**
     * Handles the subtraction of money from a balance.
     *
     * @param id     The ID of the balance.
     * @param amount The amount of money to subtract.
     * @return ResponseEntity with the updated BalanceDto if successful, HttpStatus.OK otherwise.
     */
    @PutMapping("/subtract/{id}/{amount}")
    public ResponseEntity<BalanceDto> subtractMoneyFromBalance(@PathVariable Long id, @PathVariable Double amount) {
        BalanceDto updatedBalance = balanceService.subtractMoney(id, amount);
        return ResponseEntity.ok(updatedBalance);
    }

    /**
     * Handles the transfer of money between two balances.
     *
     * @param balanceFromId The ID of the balance to transfer money from.
     * @param balanceToId   The ID of the balance to transfer money to.
     * @param amount        The amount of money to transfer.
     * @return ResponseEntity with the updated BalanceDto of the recipient balance if successful, HttpStatus.OK otherwise.
     */
    @PutMapping("/transfer/{balanceFromId}/{balanceToId}/{amount}")
    public ResponseEntity<BalanceDto> transferMoneyFromBalance(@PathVariable Long balanceFromId, @PathVariable Long balanceToId, @PathVariable Double amount) {
        BalanceDto updatedBalance = balanceService.transferMoney(balanceFromId, balanceToId, amount);
        return ResponseEntity.ok(updatedBalance);
    }

    /**
     * Handles the retrieval of balances for a specific user.
     *
     * @param userId User ID for whom the balances should be retrieved.
     * @return ResponseEntity with a list of BalanceDto for the specified user if successful, HttpStatus.OK otherwise.
     */
    @GetMapping("/getBalancesForUser/{userId}")
    public ResponseEntity<List<BalanceDto>> getBalancesForUser(@PathVariable Long userId) {
        List<BalanceDto> balanceDTOs = balanceService.getBalancesForUser(userId);
        return ResponseEntity.ok(balanceDTOs);
    }
}
