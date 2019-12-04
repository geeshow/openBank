package com.ken207.openbank.dto.response;

import com.ken207.openbank.domain.enums.BranchType;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Builder
@Getter
public class BranchCreateResponse implements CreateResponse {

    @NotEmpty
    private Long id;
    @NotEmpty
    private String name;

    private String businessNumber;
    private String taxOfficeCode;
    private String telNumber;

    @NotEmpty
    private LocalDateTime regDateTime;

    @NotEmpty
    @Enumerated(EnumType.STRING)
    private BranchType branchType;
}
