package com.ken207.openbank.mapper;

import com.ken207.openbank.domain.Customer;
import com.ken207.openbank.dto.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(source = "regBranch.name", target="regBranchName")
    @Mapping(source = "mngBranch.name", target="mngBranchName")
    @Mapping(source = "regEmployee.name", target="regEmployeeName")
    CustomerDto.Response entityToDto(Customer customer);
}
