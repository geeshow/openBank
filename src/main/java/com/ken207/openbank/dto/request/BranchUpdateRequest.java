package com.ken207.openbank.dto.request;

import com.ken207.openbank.domain.enums.BranchType;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;

@Builder
@Data
public class BranchUpdateRequest {

    private Long id;

    @NotEmpty
    private String name;

    private String businessNumber;
    private String taxOfficeCode;
    private String telNumber;

    @Enumerated(EnumType.STRING)
    private BranchType branchType;
}
