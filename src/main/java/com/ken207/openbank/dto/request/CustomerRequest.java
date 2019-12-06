package com.ken207.openbank.dto.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Builder
@Getter
public class CustomerRequest {

    @NotEmpty
    private String name;

    @NotEmpty @Email
    private String email;

    @NotEmpty
    private String nation;
}
