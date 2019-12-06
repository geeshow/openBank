package com.ken207.openbank.dto.response;

import com.ken207.openbank.domain.CustomerEntity;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Builder
@Getter
public class CustomerResponse implements CreateResponse {

    @NotEmpty
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty @Email
    private String email;
    @NotEmpty
    private String nation;
    @NotEmpty
    private String regBranchName;
    @NotEmpty
    private String mngBranchName;
    @NotEmpty
    private String regEmployeeName;
    @NotEmpty
    private LocalDateTime regDateTime;

    public static CustomerResponse transform(CustomerEntity customerEntity) {

        return CustomerResponse.builder()
                    .id(customerEntity.getId())
                    .name(customerEntity.getName())
                    .email(customerEntity.getEmail())
                    .nation(customerEntity.getNation())
                    .regBranchName(customerEntity.getRegBranchEntity().getName())
                    .mngBranchName(customerEntity.getMngBranchEntity().getName())
                    .regEmployeeName(customerEntity.getRegEmployeeEntity().getName())
                    .regDateTime(customerEntity.getRegDateTime())
                    .build();

    }
}
