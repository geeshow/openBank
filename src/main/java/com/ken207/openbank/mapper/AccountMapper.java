package com.ken207.openbank.mapper;

import com.ken207.openbank.domain.Account;
import com.ken207.openbank.dto.AccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(source = "product.productCode", target="productCode")
    @Mapping(source = "product.name", target="productName")
    @Mapping(source = "product.subjectCode", target="subjectCode")
    @Mapping(source = "product.basicRate.rate", target="basicRate")
    AccountDto.Response accountForResponse(Account account);
}
