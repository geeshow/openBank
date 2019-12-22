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

    @Mapping(source = "regBranchEntity.name", target="regBranchName")
    @Mapping(source = "mngBranchEntity.name", target="mngBranchName")
    @Mapping(source = "regEmployeeEntity.name", target="regEmployeeName")
    ProductDto.Response entityToDto(ProductEntity productEntity);
}
