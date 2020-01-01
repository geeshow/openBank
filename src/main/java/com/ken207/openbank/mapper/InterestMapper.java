package com.ken207.openbank.mapper;

import com.ken207.openbank.domain.InterestEntity;
import com.ken207.openbank.dto.InterestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InterestMapper {
    InterestMapper INSTANCE = Mappers.getMapper(InterestMapper.class);

    @Mapping(source = "accountEntity.accountNum", target="accountNum")
    @Mapping(source = "accountEntity.lastIntsDt", target="lastIntsDt")
    @Mapping(source = "accountEntity.balance", target="balance")
    @Mapping(source = "interestDetails", target="details")
    InterestDto.Response entityToDto(InterestEntity interestEntity);
}
