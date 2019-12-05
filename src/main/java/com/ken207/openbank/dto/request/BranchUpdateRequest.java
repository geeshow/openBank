package com.ken207.openbank.dto.request;

import com.ken207.openbank.domain.enums.BranchType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;

@Builder
@Getter @Setter
public class BranchUpdateRequest {

    @NotEmpty
    private Long id;

    @NotEmpty
    private String name;

    private String businessNumber;
    private String taxOfficeCode;
    private String telNumber;

    @Enumerated(EnumType.STRING)
    private BranchType branchType;
}
