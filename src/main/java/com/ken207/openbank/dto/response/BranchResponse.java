package com.ken207.openbank.dto.response;

import com.ken207.openbank.customer.Customer;
import com.ken207.openbank.domain.Branch;
import com.ken207.openbank.domain.enums.BranchType;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Builder
@Getter
public class BranchResponse implements CreateResponse {

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

    public static BranchResponse transform(Branch branch) {

        return BranchResponse.builder()
                .id(branch.getId())
                .name(branch.getName())
                .businessNumber(branch.getBusinessNumber())
                .taxOfficeCode(branch.getTaxOfficeCode())
                .telNumber(branch.getTelNumber())
                .regDateTime(branch.getRegDateTime())
                .branchType(branch.getBranchType())
                .build();

    }
}
