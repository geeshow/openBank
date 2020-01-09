package com.ken207.openbank.mapper;

import com.ken207.openbank.domain.BranchEntity;
import com.ken207.openbank.domain.TradeEntity;
import com.ken207.openbank.dto.TradeDto;
import com.ken207.openbank.dto.response.BranchResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TradeMapper {

    TradeMapper INSTANCE = Mappers.getMapper(TradeMapper.class);

    TradeDto.Response entityToResponse(TradeEntity tradeEntity);
}
