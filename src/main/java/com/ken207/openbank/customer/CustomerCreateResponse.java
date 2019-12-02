package com.ken207.openbank.customer;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Builder
@Getter
public class CustomerCreateResponse {

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

}
