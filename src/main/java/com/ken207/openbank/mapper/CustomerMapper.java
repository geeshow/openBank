package com.ken207.openbank.mapper;

import com.ken207.openbank.domain.CustomerEntity;
import com.ken207.openbank.dto.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(source = "regBranchEntity.name", target="regBranchName")
    @Mapping(source = "mngBranchEntity.name", target="mngBranchName")
    @Mapping(source = "regEmployeeEntity.name", target="regEmployeeName")
    CustomerDto.Response entityToDto(CustomerEntity customerEntity);
}
