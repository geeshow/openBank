package com.ken207.openbank.customer;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Builder
@Getter
public class CustomerDto {

    @NotEmpty
    private String name;

    @NotEmpty @Email
    private String email;

    @NotEmpty
    private String nation;
}
