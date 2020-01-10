package com.ken207.openbank.dto.request.dto.response;

import com.ken207.openbank.domain.BranchEntity;
import com.ken207.openbank.domain.enums.BranchType;
import com.ken207.openbank.dto.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@Builder @AllArgsConstructor
@NoArgsConstructor
public class BranchResponse implements BaseResponse {

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

    public static BranchResponse transform(BranchEntity branch) {

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
