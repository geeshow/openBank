package com.ken207.openbank.mapper;

import com.ken207.openbank.domain.account.AccountEntity;
import com.ken207.openbank.dto.AccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDto.Response accountForResponse(AccountEntity accountEntity);
}
