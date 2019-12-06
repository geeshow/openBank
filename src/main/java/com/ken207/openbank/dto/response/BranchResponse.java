package com.ken207.openbank.dto.response;

import com.ken207.openbank.domain.BranchEntity;
import com.ken207.openbank.domain.enums.BranchType;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@Builder @AllArgsConstructor
@NoArgsConstructor
public class BranchResponse implements CreateResponse {

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

    public static BranchResponse transform(BranchEntity branchEntity) {

        return BranchResponse.builder()
                .id(branchEntity.getId())
                .name(branchEntity.getName())
                .businessNumber(branchEntity.getBusinessNumber())
                .taxOfficeCode(branchEntity.getTaxOfficeCode())
                .telNumber(branchEntity.getTelNumber())
                .regDateTime(branchEntity.getRegDateTime())
                .branchType(branchEntity.getBranchType())
                .build();

    }
}
