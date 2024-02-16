package hr.dice.assignments.currency_exchange.services;

import hr.dice.assignments.currency_exchange.dto.BalanceDto;
import hr.dice.assignments.currency_exchange.exception.InsufficientFundsException;
import hr.dice.assignments.currency_exchange.models.BalanceEntity;
import hr.dice.assignments.currency_exchange.models.CurrencyEntity;
import hr.dice.assignments.currency_exchange.models.UserEntity;
import hr.dice.assignments.currency_exchange.properties.BalanceProperties;
import hr.dice.assignments.currency_exchange.repositories.BalanceRepository;
import hr.dice.assignments.currency_exchange.repositories.CurrencyRepository;
import hr.dice.assignments.currency_exchange.repositories.UserRepository;
import hr.dice.assignments.currency_exchange.util.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for handling balance-related operations.
 */
@Service
public class BalanceService implements hr.dice.assignments.currency_exchange.services.interfaces.BalanceService {
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;
    private final BalanceRepository balanceRepository;
    private final CurrencyExchangeService currencyExchangeService;
    private final EntityMapper mapper;
    private final BalanceProperties balanceProperties;

    /**
     * Constructor for BalanceService.
     *
     * @param userRepository           UserRepository for accessing user data.
     * @param currencyRepository       CurrencyRepository for accessing currency data.
     * @param balanceRepository        BalanceRepository for accessing balance data.
     * @param currencyExchangeService  CurrencyExchangeService for currency conversion.
     * @param balanceMapper            BalanceMapper for mapping between DTOs and entities.
     */
    @Autowired
    public BalanceService(UserRepository userRepository, CurrencyRepository currencyRepository, BalanceRepository balanceRepository,
                          CurrencyExchangeService currencyExchangeService, EntityMapper mapper, BalanceProperties balanceProperties) {
        this.balanceRepository = balanceRepository;
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.currencyRepository = currencyRepository;
        this.currencyExchangeService = currencyExchangeService;
        this.balanceProperties = balanceProperties;
    }

    /**
     * Retrieves the balance for a given balance ID.
     *
     * @param id The ID of the balance.
     * @return BalanceDto containing the balance information.
     */
    public BalanceDto getBalanceById(Long id) {
        BalanceEntity balanceEntity = balanceRepository.findById(id).orElse(null);
        return balanceEntity != null ? mapper.entityToDTO(balanceEntity) : null;
    }

    /**
     * Adds money to the balance with the specified ID.
     *
     * @param id           The ID of the balance.
     * @param amountToAdd  The amount of money to add.
     * @return BalanceDto containing the updated balance information.
     */
    @Transactional
    public BalanceDto addMoney(Long id, Double amountToAdd) {
        BalanceEntity balanceEntity = balanceRepository.findById(id).orElse(null);

        if (balanceEntity != null) {
            Double currentBalance = balanceEntity.getAmount();
            Double newBalance = currentBalance + amountToAdd;

            balanceEntity.setAmount(newBalance);
            balanceRepository.save(balanceEntity);

            return mapper.entityToDTO(balanceEntity);
        }

        return null;
    }

    /**
     * Subtracts money from the balance with the specified ID.
     *
     * @param id                The ID of the balance.
     * @param amountToSubtract  The amount of money to subtract.
     * @return BalanceDto containing the updated balance information.
     * @throws InsufficientFundsException if the balance is insufficient and payments without funds are not allowed.
     */
    @Transactional
    public BalanceDto subtractMoney(Long id, Double amountToSubtract) {
        BalanceEntity balanceEntity = balanceRepository.findById(id).orElse(null);

        if (balanceEntity != null) {
            Double currentBalance = balanceEntity.getAmount();

            if ((currentBalance - amountToSubtract) < 0) {
                if (balanceProperties.isAllowSubtractWithoutFunds()) {
                    Double newBalance = currentBalance - amountToSubtract;

                    balanceEntity.setAmount(newBalance);
                    balanceRepository.save(balanceEntity);

                    return mapper.entityToDTO(balanceEntity);
                } else {
                    throw new InsufficientFundsException("Insufficient funds");
                }
            } else {
                Double newBalance = currentBalance - amountToSubtract;
                balanceEntity.setAmount(newBalance);
                balanceRepository.save(balanceEntity);

                return mapper.entityToDTO(balanceEntity);
            }
        }

        return null;
    }

    /**
     * Transfers money from one balance to another.
     *
     * @param balanceFromId  The ID of the balance to transfer money from.
     * @param balanceToId    The ID of the balance to transfer money to.
     * @param amount         The amount of money to transfer.
     * @return BalanceDto containing the updated balance information of the recipient balance.
     */
    @Transactional
    @Override
    public BalanceDto transferMoney(Long balanceFromId, Long balanceToId, Double amount) {
        BalanceEntity balanceFrom = balanceRepository.findById(balanceFromId).orElse(null);
        BalanceEntity balanceTo = balanceRepository.findById(balanceToId).orElse(null);

        if (balanceFrom != null && balanceTo != null) {
            if (!balanceFrom.getCurrency().getCode().equals(balanceTo.getCurrency().getCode())) {
                Double convertedAmount = currencyExchangeService.convertCurrency(
                        balanceFrom.getCurrency().getCode(),
                        balanceTo.getCurrency().getCode(),
                        amount
                );

                subtractMoney(balanceFromId, convertedAmount);

                addMoney(balanceToId, convertedAmount);

                return getBalanceById(balanceToId);
            } else {
                subtractMoney(balanceFromId, amount);
                addMoney(balanceToId, amount);

                return getBalanceById(balanceToId);
            }
        }

        return null;
    }

    /**
     * Retrieves a list of balances associated with a user.
     *
     * @param userId The ID of the user.
     * @return List of BalanceDto objects representing the balances associated with the user.
     */
    @Override
    public List<BalanceDto> getBalancesForUser(Long userId) {
        UserEntity userEntity = userRepository.getById(userId);

        List<BalanceEntity> balances = balanceRepository.findByUser(userEntity);

        return balances.stream()
                .map(mapper::entityToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Inserts a new balance for a user.
     *
     * @param newBalanceDto BalanceDto containing the information of the new balance.
     * @param userId        The ID of the user for whom the new balance is inserted.
     * @return BalanceDto containing the information of the newly inserted balance.
     */
    @Override
    public BalanceDto insert(BalanceDto newBalanceDto, Long userId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        if(!user.isPresent()) {
            return null;
        }

        BalanceEntity balanceEntity = new BalanceEntity();
        balanceEntity.setName(newBalanceDto.getName());
        balanceEntity.setAmount(0d);
        balanceEntity.setUser(user.get());

        Optional<CurrencyEntity> currencyEntity = currencyRepository.findByCode(newBalanceDto.getCurrencyCode());

        currencyEntity.ifPresent(balanceEntity::setCurrency);
        balanceRepository.save(balanceEntity);
        return mapper.entityToDTO(balanceEntity);
    }

}
