package com.ken207.openbank.mapper;

import com.ken207.openbank.domain.Branch;
import com.ken207.openbank.dto.request.BranchRequest;
import com.ken207.openbank.dto.response.BranchResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BranchMapper {

    BranchMapper INSTANCE = Mappers.getMapper(BranchMapper.class);

    BranchResponse entityToResponse(Branch branch);
    void updateBranchFromDto(BranchRequest branchRequest, @MappingTarget Branch branch);
}
