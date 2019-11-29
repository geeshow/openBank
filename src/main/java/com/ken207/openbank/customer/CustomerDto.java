package com.ken207.openbank.customer;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Builder
@Getter
public class CustomerDto {

    @NotEmpty
    private String name;

    @NotEmpty
    private String email;

    public CustomerDto(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
