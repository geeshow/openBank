package com.ken207.openbank.dto.response;

import com.ken207.openbank.domain.Customer;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Builder
@Getter
public class CustomerResponse implements BaseResponse {

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

    public static CustomerResponse transform(Customer customer) {

        return CustomerResponse.builder()
                    .id(customer.getId())
                    .name(customer.getName())
                    .email(customer.getEmail())
                    .nation(customer.getNation())
                    .regBranchName(customer.getRegBranch().getName())
                    .mngBranchName(customer.getMngBranch().getName())
                    .regEmployeeName(customer.getRegEmployee().getName())
                    .regDateTime(customer.getRegDateTime())
                    .build();

    }
}
