package com.ken207.openbank.mapper;

import com.ken207.openbank.domain.CustomerEntity;
import com.ken207.openbank.domain.ProductEntity;
import com.ken207.openbank.dto.CustomerDto;
import com.ken207.openbank.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "basicRate.rate", target="basicRate")
    ProductDto.Create entityToDto(ProductEntity productEntity);
}
