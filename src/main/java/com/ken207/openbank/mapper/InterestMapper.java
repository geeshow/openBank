package com.ken207.openbank.mapper;

import com.ken207.openbank.domain.InterestEntity;
import com.ken207.openbank.dto.InterestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InterestMapper {
    InterestMapper INSTANCE = Mappers.getMapper(InterestMapper.class);

    @Mapping(source = "account.accountNum", target="accountNum")
    @Mapping(source = "account.lastIntsDt", target="lastIntsDt")
    @Mapping(source = "account.balance", target="balance")
    @Mapping(source = "interest", target="expectedInterest")
    @Mapping(source = "interestDetails", target="details")
    InterestDto.Response entityToDto(InterestEntity interestEntity);

    @Mapping(source = "account.accountNum", target="accountNum")
    InterestDto.Dto entityToDtoForList(InterestEntity interestEntity);
}
