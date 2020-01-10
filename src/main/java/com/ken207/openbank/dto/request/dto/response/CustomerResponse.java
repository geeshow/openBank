package com.ken207.openbank.dto.request.dto.response;

import com.ken207.openbank.domain.CustomerEntity;
import com.ken207.openbank.dto.response.BaseResponse;
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

    public static CustomerResponse transform(CustomerEntity customer) {

        return CustomerResponse.builder()
                    .id(customer.getId())
                    .name(customer.getName())
                    .email(customer.getEmail())
                    .nation(customer.getNation())
                    .regBranchName(customer.getRegBranchEntity().getName())
                    .mngBranchName(customer.getMngBranchEntity().getName())
                    .regEmployeeName(customer.getRegEmployeeEntity().getName())
                    .regDateTime(customer.getRegDateTime())
                    .build();

    }
}
