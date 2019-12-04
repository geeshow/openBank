package com.ken207.openbank.dto.request;

import com.ken207.openbank.domain.enums.BranchType;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Builder
@Getter
public class BranchCreateRequest {

    @NotEmpty
    private String name;

    private String businessNumber;
    private String taxOfficeCode;
    private String telNumber;

    @Enumerated(EnumType.STRING)
    private BranchType branchType;
}
