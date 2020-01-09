package com.ken207.openbank.mapper;

import com.ken207.openbank.domain.Trade;
import com.ken207.openbank.dto.TradeDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TradeMapper {

    TradeMapper INSTANCE = Mappers.getMapper(TradeMapper.class);

    TradeDto.Response entityToResponse(Trade trade);
}
