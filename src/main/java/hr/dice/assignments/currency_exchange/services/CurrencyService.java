package hr.dice.assignments.currency_exchange.services;

import hr.dice.assignments.currency_exchange.dto.CurrencyDto;
import hr.dice.assignments.currency_exchange.models.BalanceEntity;
import hr.dice.assignments.currency_exchange.models.CurrencyEntity;
import hr.dice.assignments.currency_exchange.repositories.CurrencyRepository;
import hr.dice.assignments.currency_exchange.util.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing CurrencyEntity instances.
 */
@Service
public class CurrencyService implements hr.dice.assignments.currency_exchange.services.interfaces.CurrencyService {
    private EntityMapper mapper;

    private final CurrencyRepository currencyRepository;

    /**
     * Constructor for CurrencyService.
     *
     * @param currencyRepository Repository for accessing CurrencyEntity data.
     */
    @Autowired
    public CurrencyService(EntityMapper mapper, CurrencyRepository currencyRepository) {
        this.mapper = mapper;
        this.currencyRepository = currencyRepository;
    }

    /**
     * Retrieves all CurrencyEntity instances.
     *
     * @return List of CurrencyDto instances.
     */
    public List<CurrencyDto> getAll() {
        List<CurrencyEntity> currencies = currencyRepository.findAll();
        return currencies.stream()
                .map(mapper::entityToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a CurrencyEntity instance by its ID.
     *
     * @param id ID of the CurrencyEntity.
     * @return CurrencyDto if found, null otherwise.
     */
    public CurrencyDto getById(Long id) {
        Optional<CurrencyEntity> entity = currencyRepository.findById(id);
        return entity.isPresent() ?  mapper.entityToDTO(entity.get()) : null;
    }

    /**
     * Retrieves a CurrencyEntity instance by its code.
     *
     * @param code Code of the CurrencyEntity.
     * @return CurrencyDto if found, null otherwise.
     */
    public CurrencyDto getByCode(String code) {
        Optional<CurrencyEntity> entity = currencyRepository.findByCode(code);
        return entity.isPresent() ?  mapper.entityToDTO(entity.get()) : null;
    }
}
