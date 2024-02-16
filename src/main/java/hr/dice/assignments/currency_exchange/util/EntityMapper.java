package hr.dice.assignments.currency_exchange.util;

import hr.dice.assignments.currency_exchange.dto.BalanceDto;
import hr.dice.assignments.currency_exchange.dto.CurrencyDto;
import hr.dice.assignments.currency_exchange.dto.UserDto;
import hr.dice.assignments.currency_exchange.models.BalanceEntity;
import hr.dice.assignments.currency_exchange.models.CurrencyEntity;
import hr.dice.assignments.currency_exchange.models.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Mapper interface for converting between Entity and Dto objects.
 */
@Mapper(componentModel = "spring", imports = List.class)
public interface EntityMapper {

    /**
     * Singleton instance of the EntityMapper.
     */
    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

    /**
     * Maps a BalanceEntity to a BalanceDto.
     *
     * @param balanceEntity BalanceEntity instance to be mapped.
     * @return Corresponding BalanceDto instance.
     */
    @Mapping(source = "currency.code", target = "currencyCode")
    @Mapping(source = "user.id", target = "userId")
    BalanceDto entityToDTO(BalanceEntity balanceEntity);

    UserDto entityToDTO(UserEntity userEntity);

    CurrencyDto entityToDTO(CurrencyEntity currencyEntity);
}
